package server.handler;

import common.messages.Message;
import org.apache.log4j.Logger;
import server.Server.ServerHandler;

import java.net.Socket;


/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public interface ClientMessageHandler {
    Logger logger = Logger.getLogger(ClientMessageHandler.class);
    void handle(ServerHandler serverHandler, Socket clientSocket, Message message);
}
