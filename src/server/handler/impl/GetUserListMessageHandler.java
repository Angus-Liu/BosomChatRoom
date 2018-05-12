package server.handler.impl;

import common.messages.Message;
import common.messages.MessageType;
import server.Server;
import server.Server.ServerHandler;
import server.handler.ClientMessageHandler;

import java.net.Socket;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public class GetUserListMessageHandler implements ClientMessageHandler {

    @Override
    public void handle(ServerHandler serverHandler, Socket clientSocket, Message clientMessage) {
        Message serverMessage = new Message();
        serverMessage.setType(MessageType.USER_LIST);
        serverMessage.setUserList(Server.getSignInUserList());
        serverHandler.sendMessage(serverMessage);
    }
}
