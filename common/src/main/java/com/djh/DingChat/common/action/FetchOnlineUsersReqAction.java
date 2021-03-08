package com.djh.DingChat.common.action;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class FetchOnlineUsersReqAction extends Action{

    public FetchOnlineUsersReqAction(){
        this.setActionType("");
        this.setAction(ActionIdEnum.ACTION_FETCH_ONLINE_USERS_REQ.getAction());
        this.setRequestId(UUID.randomUUID().toString());
    }

    //扩展
    private int page;

    private int count;
}
