package com.djh.DingChat.common.event;

import io.netty.channel.Channel;

//规范化事件
public interface IEvent<T,R> {
    /**
     * 处理事件业务
     * @param request
     * @param channel
     * @return
     */
    R handle(final T request, final Channel channel);
}
