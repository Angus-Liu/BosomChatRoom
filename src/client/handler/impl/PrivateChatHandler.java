package client.handler.impl;

import client.Client;
import client.handler.ServerMessageHandler;
import common.messages.Message;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public class PrivateChatHandler implements ServerMessageHandler {


    @Override
    public void handle(Message message) {
        logger.info("接收到私聊消息！[ " + message.getFrom() + "：" + message.getContent() + " ]");
    }
}
