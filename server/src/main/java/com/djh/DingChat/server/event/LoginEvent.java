package com.djh.DingChat.server.event;

import com.alibaba.fastjson.JSONObject;
import com.djh.DingChat.common.action.Action;
import com.djh.DingChat.common.action.LoginReqAction;
import com.djh.DingChat.common.action.LoginRespAction;
import com.djh.DingChat.common.connection.ConnectionPool;
import com.djh.DingChat.common.event.IEvent;
import io.netty.channel.Channel;

public class LoginEvent implements IEvent<Action, Action> {
    @Override
    public Action handle(Action action, Channel channel) {
        System.out.println("receive action: "+action);
        LoginReqAction reqAction = JSONObject.parseObject(action.getPayload(),LoginReqAction.class);

        //TODO: UserService User

        LoginRespAction respAction = new LoginRespAction();
        respAction.setResult(false);




        //登陆成功添加到连接添加到连接池
        System.out.println("login success with mobile: ");
        //ConnectionPool.getInstance().add(userId,channel);

        //返回
        //respAction.setUserId();
        //respAction.setResult();
        //respAction.setPayload();
        return respAction;
    }
}
