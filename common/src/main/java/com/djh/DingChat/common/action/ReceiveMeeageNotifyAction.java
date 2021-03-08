package com.djh.DingChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class ReceiveMeeageNotifyAction extends Action{

    public ReceiveMeeageNotifyAction(){
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_RECEIVE_MESSAGE_NOTIFY_REQ.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    private String fromUserId;

    private String mobile;

    private String messageId;

    private String messageType;

    private String message;
}
