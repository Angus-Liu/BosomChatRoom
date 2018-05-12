package client.handler.impl;

import client.Client;
import client.handler.ServerMessageHandler;
import common.messages.Message;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/12
 * Version: v1.0
 */
public class SignInResultMessageHandler implements ServerMessageHandler {
    @Override
    public void handle(Message message) {
        if (message.getResult()) {
            // 登录成功
            logger.info("登录成功！[ " + message.getUser() + " ]");
            Client.createConnection(message.getUser());
        } else {
            // 登录失败
            logger.info("登录失败！[ " + message.getReason() + " ]");
        }
    }
}
