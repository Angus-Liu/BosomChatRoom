package server.handler;

import common.messages.MessageType;
import server.handler.impl.*;

import java.util.HashMap;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public class ClientMessageHandlerFactory {
    private static HashMap<MessageType, ClientMessageHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put(MessageType.SIGN_UP, new SignUpMessageHandler());
        handlerMap.put(MessageType.SIGN_IN, new SignInMessageHandler());
        handlerMap.put(MessageType.SIGN_OUT, new SignOutMessageHandler());
        handlerMap.put(MessageType.PRIVATE_CHAT, new PrivateChatHandler());
        handlerMap.put(MessageType.GROUP_CHAT, new GroupChatMessageHandler());
        handlerMap.put(MessageType.GET_USER_INFO, new GetUserInfoHandler());
        handlerMap.put(MessageType.GET_USER_LIST, new GetUserListMessageHandler());
    }

    public static ClientMessageHandler getInstance(MessageType type){
        return handlerMap.get(type);
    }
}
