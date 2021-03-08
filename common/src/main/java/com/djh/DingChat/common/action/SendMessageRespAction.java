package com.djh.DingChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class SendMessageRespAction extends Action{

    public SendMessageRespAction(){
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_SEND_MESSAGE_RESP.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    private Boolean result;

    private Long messageId;
}
