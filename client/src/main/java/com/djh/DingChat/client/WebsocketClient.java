package com.djh.DingChat.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djh.DingChat.common.action.Action;
import com.djh.DingChat.common.event.EventPool;
import com.djh.DingChat.common.event.IEvent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.URI;

public class WebsocketClient {

    private URI uri;

    private Bootstrap bootstrap;
    private EventLoopGroup group;

    private ChannelPromise channelPromise;
    private Channel channel;

    public WebsocketClient(final URI uri) {
        this.uri = uri;
        this.init();
    }

    public void connect(){
        try {
            channel = bootstrap.connect(uri.getHost(),uri.getPort()).sync().channel();
            channelPromise.sync();
            System.out.println("connection success! and handshake complete~!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void init(){
        bootstrap = new Bootstrap();
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
        bootstrap.option(ChannelOption.TCP_NODELAY,true);

        group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new HttpClientCodec());
                        pipeline.addLast(new HttpObjectAggregator(64*1024));//数据不超过64k
                        pipeline.addLast(new WebSocketHandlerClient(getHandshaker(uri)));
                    }

                    private WebSocketClientHandshaker getHandshaker(final URI uri){
                        return WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13,null,false,null);
                    }
                });

    }

    private class WebSocketHandlerClient extends SimpleChannelInboundHandler<Object> {

        private WebSocketClientHandshaker handshaker;

        public WebSocketHandlerClient(final WebSocketClientHandshaker handshaker){
            this.handshaker = handshaker;
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            channelPromise = ctx.newPromise();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            this.handshaker.handshake(ctx.channel());
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
            System.out.println("receive data:"+o+" from address: "+ctx.channel().remoteAddress());
            //确认握手包
            if (!handshaker.isHandshakeComplete()){
                try {
                    handshaker.finishHandshake(ctx.channel(),(FullHttpResponse) o);
                    channelPromise.setSuccess();
                    System.out.println("handshake success!");
                } catch (Exception e) {
                    e.printStackTrace();
                    channelPromise.setFailure(e);
                }
                return;
            }

            //消息处理
            if(!(o instanceof TextWebSocketFrame)){
                System.out.println("receive error type message. o:"+o);
                return;
            }
            TextWebSocketFrame request = (TextWebSocketFrame) o;
            System.out.println("received data:"+request.text()+"from address: "+ctx.channel().remoteAddress());
            Action action;
            action = JSONObject.parseObject(request.text(),Action.class);
            IEvent event = EventPool.getInstance().find(action.getAction());
            if (null == event ){
                System.out.println("no event found for key: "+action.getAction());
                return;
            }
            Action respAction = (Action) event.handle(action,ctx.channel());
            if (null!=respAction){
                System.out.println("resp action: "+action);
                ctx.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(respAction)));
            }


        }
    }



}
