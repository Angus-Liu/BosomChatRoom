package client;

import client.handler.ServerMessageHandler;
import client.handler.ServerMessageHandlerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import common.messages.Message;
import common.messages.MessageType;
import common.messages.User;
import common.test.MessageTest;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/10
 * Version: v1.0
 */
public class Client {

    // 日志
    private static final Logger logger = Logger.getLogger(Client.class);

    private static final String IP = "192.168.40.1"; // 服务IP地址，默认为本地地址

    private static final int PORT = 9090; // 服务器端口地址

    private static Gson gson = new Gson();

    private static User clientUser;

    private static Socket clientSocket;

    private static BufferedReader reader;

    private static PrintWriter writer;


    public static void main(String[] args) {

        try {
            clientLaunch();
        } catch (IOException e) {
            logger.error("客户端启动异常！[ " + e.getMessage() + " ]");
        }
    }

    public static void clientLaunch() throws IOException {
        clientSocket = new Socket(IP, PORT);
        reader = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream(), "UTF-8"));
        writer = new PrintWriter(
                clientSocket.getOutputStream(), true);

        // 开启处理线程，接收信息
        new Thread(new ClientHandler()).start();

//        sendMessage(MessageTest.signUpMessage());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        sendMessage(MessageTest.signInMessage());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        sendMessage(MessageTest.privateChatMessage(clientUser));
//        sendMessage(MessageTest.GroupChatMessage(clientUser));
//        sendMessage(MessageTest.GetUserListMessage(clientUser));
    }



    public static void sendMessage(Message clientMessage) {
        if (!clientSocket.isClosed()) {
            String clientMessageJson = gson.toJson(clientMessage);
            writer.println(clientMessageJson);
            logger.info("发送消息！[ " + clientMessageJson + " ]");
        }
    }


    public static void createConnection(User user) {
        clientUser = user;
    }

    // 关闭连接
    public static void closeConnection() throws IOException {
        Message message = new Message();
        message.setType(MessageType.SIGN_OUT);
        sendMessage(message);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientSocket.close();
    }

    public static class ClientHandler implements Runnable {

        @Override
        public void run() {
            while (!clientSocket.isClosed()) {
                receiveMessage();
            }
        }

        public void receiveMessage() {
            String serverMessageJson = null;
            try {
                serverMessageJson = reader.readLine();
                logger.info("接收服务器消息！[ " + serverMessageJson + " ]");
                Message serverMessage = gson.fromJson(serverMessageJson, Message.class);
                ServerMessageHandler messageHandler = ServerMessageHandlerFactory.getInstance(serverMessage.getType());
                messageHandler.handle(serverMessage);
            } catch (JsonSyntaxException e) {
                logger.error("错误的json消息！[" + serverMessageJson + "]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
