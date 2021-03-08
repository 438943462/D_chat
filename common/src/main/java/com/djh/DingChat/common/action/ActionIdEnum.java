package com.djh.DingChat.common.action;

import lombok.Getter;

public enum ActionIdEnum {

    /**
     * 登录请求
     */
    ACTION_LOGIN_RED("login_req","登录请求"),
    
    ACTION_LOGIN_RESP("login_resp","登陆响应"),
    ACTION_FETCH_ONLINE_USERS_REQ("fetch_online_users_req","获取在线用户列表"),
    ACTION_FETCH_ONLINE_USERS_RESP("fetch_online_users_resp","获取在线用户列表响应"),
    ACTION_SEND_MESSAGE_REQ("send_msg_req","发送消息请求"),
    ACTION_SEND_MESSAGE_RESP("send_msg_resp","发送消息响应"),
    ACTION_RECEIVE_MESSAGE_NOTIFY_REQ("receive_message_notify","请求推送消息"),
    ACTION_RECEIVE_MESSAGE_NOTIFY_ACK("receive_message_notify_ack","响应推送请求")
    

    ;

    @Getter
    private String action;

    @Getter
    private String desc;

    ActionIdEnum(final String action, final String desc){
        this.action = action;
        this.desc = desc;
    }
}
