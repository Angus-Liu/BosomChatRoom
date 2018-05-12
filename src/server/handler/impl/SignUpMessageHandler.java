package server.handler.impl;

import common.messages.Message;
import common.messages.MessageType;
import common.messages.User;
import server.Server.ServerHandler;
import server.dao.UserDao;
import server.dao.UserDaoImpl;
import server.handler.ClientMessageHandler;

import java.net.Socket;
import java.sql.SQLException;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public class SignUpMessageHandler implements ClientMessageHandler {

    UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public void handle(ServerHandler serverHandler, Socket clientSocket, Message clientMessage) {
        User signUpUser = clientMessage.getUser();
        Message serverMessage = new Message();
        serverMessage.setType(MessageType.SIGN_UP_RESULT);
        try {
            User user = userDao.findByAccount(signUpUser.getAccount());
            if (user != null) { // 注册失败，返回原因
                serverMessage.setResult(false);
                serverMessage.setReason("该账号已存在!");
            } else { // 注册成功
                userDao.add(signUpUser);
                serverMessage.setResult(true);
            }
        } catch (SQLException e) {
            serverMessage.setResult(false);
            serverMessage.setReason("数据库异常！[" + e.getMessage() + "]");

            logger.error("数据库异常！[" + e.getMessage() + "]");
        }
        serverHandler.sendMessage(serverMessage);
    }
}
