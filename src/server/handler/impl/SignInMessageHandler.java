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
public class SignInMessageHandler implements ClientMessageHandler {

    UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public void handle(ServerHandler serverHandler, Socket clientSocket, Message clientMessage) {
        User signInUser = clientMessage.getUser();
        Message serverMessage = new Message();
        serverMessage.setType(MessageType.SIGN_IN_RESULT);

        if (serverHandler.userHasSignIn(signInUser)) {
            serverMessage.setResult(false);
            serverMessage.setReason("用户已登录！");
        } else {
            try {
                User user = userDao.findByAccount(signInUser.getAccount());
                if (user == null || !user.getPassword().equals(signInUser.getPassword())) { // 登录失败，返回原因
                    serverMessage.setResult(false);
                    serverMessage.setReason("账号或密码错误！");
                } else { // 登录成功，返回用户完整信息
                    serverMessage.setResult(true);
                    serverMessage.setUser(user);
                    serverHandler.createConnection(user);
                }
            } catch (SQLException e) {
                serverMessage.setResult(false);
                serverMessage.setReason("数据库异常！["+e.getMessage()+"]");
                logger.error("数据库异常！[" + e.getMessage() + "]");
            }
        }
        serverHandler.sendMessage(serverMessage, clientSocket);
    }
}
