package com.tickychat.server.web.netty.bean;

import lombok.Data;

import java.util.Date;

/**
 * 聊天消息
 *
 * @author Angus
 * @date 2018/12/20
 */
@Data
public class ChatMsg {
    /**
     * 用于签收
     */
    private String msgId;
    /**
     * 用于发送
     */
    private String sendUserId;
    private String acceptUserId;
    private String msg;
    private Date sendTime;
}
