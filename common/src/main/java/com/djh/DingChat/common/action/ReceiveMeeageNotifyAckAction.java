package com.djh.DingChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class ReceiveMeeageNotifyAckAction extends Action{

    public ReceiveMeeageNotifyAckAction(){
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_RECEIVE_MESSAGE_NOTIFY_ACK.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    private String messageId;

}
