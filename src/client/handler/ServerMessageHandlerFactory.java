package client.handler;

import client.handler.impl.*;
import common.messages.MessageType;

import java.util.HashMap;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public class ServerMessageHandlerFactory {

    private static HashMap<MessageType, ServerMessageHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put(MessageType.GROUP_CHAT, new GroupChatMessageHandler());

        handlerMap.put(MessageType.HANDLE_RESULT, new HandleResultMessageHandler());

        handlerMap.put(MessageType.NOTIFICATION, new NotifIcationMessageHandler());

        handlerMap.put(MessageType.PRIVATE_CHAT, new PrivateChatHandler());

        handlerMap.put(MessageType.SIGN_IN_RESULT, new SignInResultMessageHandler());

        handlerMap.put(MessageType.SIGN_UP_RESULT, new SignUpResultMessageHandler());

        handlerMap.put(MessageType.USER_INFO, new UserInfoMessageHandler());

        handlerMap.put(MessageType.USER_LIST, new UserListMessageHandler());
    }

    public static ServerMessageHandler getInstance(MessageType type) {

        return handlerMap.get(type);

    }
}
