package server.handler.impl;

import common.messages.Message;
import server.Server.ServerHandler;
import server.handler.ClientMessageHandler;

import java.net.Socket;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public class SignOutMessageHandler implements ClientMessageHandler {

    @Override
    public void handle(ServerHandler serverHandler, Socket clientSocket, Message clientMessage) {
        serverHandler.closeConnection();
    }
}
