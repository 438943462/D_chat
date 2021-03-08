package com.djh.DingChat.common.action;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Action {

    private String actionType;

    private String action;

    private String requestId;

    /**
     * payload为json
     */
    private String payload;


}
