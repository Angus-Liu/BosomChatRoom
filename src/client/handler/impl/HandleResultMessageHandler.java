package client.handler.impl;

import client.Client;
import client.handler.ServerMessageHandler;
import common.messages.Message;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public class HandleResultMessageHandler implements ServerMessageHandler {

    @Override
    public void handle(Message serverMessage) {
        if(serverMessage.getResult()){

        } else {

        }
        logger.info(serverMessage.getReason());
    }
}
