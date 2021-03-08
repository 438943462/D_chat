package com.djh.DingChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class FetchOnlineUsersRespAction extends Action{

    public FetchOnlineUsersRespAction(){
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_FETCH_ONLINE_USERS_RESP.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    //TODO:
}
