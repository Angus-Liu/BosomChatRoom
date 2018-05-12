package server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import common.messages.Message;
import common.messages.User;
import org.apache.log4j.Logger;
import server.handler.ClientMessageHandler;
import server.handler.ClientMessageHandlerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/10
 * Version: v1.0
 */
public class Server {
    // 日志
    private static final Logger logger = Logger.getLogger(Server.class);

    // 借助Gson解析json数据
    private static Gson gson = new Gson();

    // 服务端默认端口号
    private static final int PORT = 9090;

    // 用户账号与客户端Socket对象的映射
    private static Map<String, Socket> userAccountSocketMap = new HashMap<>();

    // 在线用户列表
    private static List<User> signInUserList = new LinkedList<>();

    // 服务端Socket
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws Exception {
        try {
            serverLaunch();
        } catch (IOException e) {
            logger.error("服务端启动异常！[" + e.getMessage() + "]");
        } finally {
            if (serverSocket != null) serverSocket.close();
        }
    }

    private static void serverLaunch() throws IOException {
        serverSocket = new ServerSocket(PORT);

        logger.info("server启动！[" + InetAddress.getLocalHost().getHostAddress() + "/" + serverSocket.getLocalPort() + " ]");

        while (true) {
            // 服务端阻塞等待客户端连接，每接收一个clientSocket就开启一个线程
            new Thread(new ServerHandler(serverSocket.accept())).start();
        }
    }

    // 获取在线用户列表
    public static List<User> getSignInUserList(){
        return signInUserList;
    }

    public static class ServerHandler implements Runnable {

        // 当前线程所处理的客户端Socket
        private Socket clientSocket;

        // 当前线程所处理的客户端用户
        private User clientUser;

        ServerHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            logger.info("接受客服端socket连接！[" + clientSocket + " ]");
        }

        @Override
        public void run() {
            while (!clientSocket.isClosed()) {
                receiveMessage();
            }

        }

        // 检查用户是否在线
        public boolean userHasSignIn(User user) {
            return userAccountSocketMap.containsKey(user.getAccount());
        }


        // 接受客戶端socket中的消息，并交给对应的MessageHandler处理
        public void receiveMessage() {
            String clientMessageJson = null;
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream(), "utf-8"));

                clientMessageJson = reader.readLine();
                logger.info("接收客服端消息！[ " + clientMessageJson + " ]");

                // 将json解析为Message对象
                Message clientMessage = gson.fromJson(clientMessageJson, Message.class);
                // 根据不同的消息类型，将消息交给不同的MessageHandler处理
                ClientMessageHandler messageHandler = ClientMessageHandlerFactory.getInstance(clientMessage.getType());
                messageHandler.handle(this, clientSocket, clientMessage);
            } catch (JsonSyntaxException e) {
                logger.error("错误的json消息！[ " + clientMessageJson + " ]");
            } catch (SocketException e) {
                logger.error("客户端异常退出！[ " + clientSocket + " ]");
                closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 将消息通过当前客户端socket发送
        public void sendMessage(Message serverMessage) {
            sendMessage(serverMessage, clientSocket);
        }

        // 将消息通过指定用户账号发送
        public void sendMessage(Message serverMessage, String to) {
            Socket socket = userAccountSocketMap.get(to);
            sendMessage(serverMessage, socket);
        }

        // 将消息通过指定客户端socket发送
        public void sendMessage(Message serverMessage, Socket clientSocket) {
            try {
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                String serverMessageJson = gson.toJson(serverMessage);
                writer.println(serverMessageJson);
                logger.info("消息发送成功！[ " + serverMessageJson + " ]");
            } catch (IOException e) {
                logger.error("消息发送失败！[ " + e.getMessage() + " ]");
            }
        }

        // 将消息通过除本客户端socket之外的客户端socket发送
        public void sendMessageAll(Message serverMessage) {
            for (Socket socket : userAccountSocketMap.values()) {
                if (socket != clientSocket) {
                    sendMessage(serverMessage, socket);
                }
            }
        }

        // 建立连接
        public synchronized void createConnection(User user) {
            clientUser = user;
            userAccountSocketMap.put(user.getAccount(), clientSocket);
            signInUserList.add(user);
            logger.info("用户登录！[" + user.getAccount() + "]");
        }


        // 关闭连接
        public synchronized void closeConnection() {
            if (clientUser != null) {
                signInUserList.remove(clientUser);
                userAccountSocketMap.remove(clientUser.getAccount());
                logger.info("用户登出！[ " + clientUser.getAccount() + " ]");
            } else {
            }
            try {
                clientSocket.close();
                logger.info("客户端断开连接！[ " + clientSocket + " ]");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
