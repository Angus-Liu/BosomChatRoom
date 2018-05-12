package client.handler.impl;

import client.handler.ServerMessageHandler;
import common.messages.Message;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/12
 * Version: v1.0
 */
public class SignUpResultMessageHandler implements ServerMessageHandler {
    @Override
    public void handle(Message message) {
        if (message.getResult()){
            // 注册成功
        } else {
            // 注册失败
            logger.info(message.getReason());
        }
    }
}
