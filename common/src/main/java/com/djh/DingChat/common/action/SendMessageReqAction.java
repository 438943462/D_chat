package com.djh.DingChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class SendMessageReqAction extends Action{

    public SendMessageReqAction(){
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_SEND_MESSAGE_REQ.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    private String toUserId;

    private String messageType;

    private String message;
}
