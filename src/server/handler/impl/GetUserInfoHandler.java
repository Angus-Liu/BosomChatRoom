package server.handler.impl;

import common.messages.Message;
import server.Server.ServerHandler;
import server.handler.ClientMessageHandler;

import java.net.Socket;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/12
 * Version: v1.0
 */
public class GetUserInfoHandler implements ClientMessageHandler {
    @Override
    public void handle(ServerHandler serverHandler, Socket clientSocket, Message message) {

    }
}
