package com.djh.DingChat.server.server;

import com.djh.DingChat.common.connection.ConnectionPool;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

//这里可以让连接池用@Service 实现单例
public class WebSocketHandlerServer extends SimpleChannelInboundHandler<Object> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        System.out.println("connected from address:"+ctx.channel().remoteAddress());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connected closed with address:"+ctx.channel().remoteAddress());
        ConnectionPool.getInstance().removeByChannelId(ctx.channel().id().asLongText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        System.out.println("receive data:"+o+" from address: "+ctx.channel().remoteAddress());
        if(!(o instanceof TextWebSocketFrame)){
            System.out.println("receive error type message. o:"+o);
            return;
        }
        TextWebSocketFrame request = (TextWebSocketFrame) o;
        System.out.println("received text:"+request.text());
        ctx.writeAndFlush(new TextWebSocketFrame("服务端返回: "+request.text()));
    }
}
