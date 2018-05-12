package client.handler;

import common.messages.Message;
import org.apache.log4j.Logger;


/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public interface ServerMessageHandler {
    Logger logger = Logger.getLogger(ServerMessageHandler.class);
    void handle(Message message);
}
