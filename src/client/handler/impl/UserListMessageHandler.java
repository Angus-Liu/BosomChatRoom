package client.handler.impl;

import client.handler.ServerMessageHandler;
import common.messages.Message;

import java.net.Socket;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public class UserListMessageHandler implements ServerMessageHandler {

    @Override
    public void handle(Message serverMessage) {
        logger.info("获取用户列表信息！[ " + serverMessage.getUserList() + " ]");
    }
}
