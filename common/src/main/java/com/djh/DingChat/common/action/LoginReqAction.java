package com.djh.DingChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class LoginReqAction extends Action{

    public LoginReqAction(){
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_LOGIN_RED.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    private String mobile;

    private String password;
}
