package common.test;

import common.messages.*;
import javafx.scene.input.DataFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/10
 * Version: v1.0
 */
public class MessageTest {

    private static Scanner in = new Scanner(System.in);

    public static final String SIGN_UP = "{" +
            "\"type\":\"SIGN_UP\"," +
            "\"user\":{" +
            "\"nickname\":\"Angus Liu\"," +
            "\"account\":\"angus.liu97@gmail.com\"," +
            "\"password\":\"123456\"" +
            "}" +
            "}";

    public static final String SIGN_IN = "{" +
            "\"type\":\"SIGN_IN\"," +
            "\"user\":{" +
            "\"nickname\":\"Angus Liu\"," +
            "\"account\":\"angus.liu96@gmail.com\"," +
            "\"password\":\"123456\"" +
            "}" +
            "}";

    public static final String SIGN_OUT = "{" +
            "\"type\":\"SIGN_OUT\"," +
            "\"from\":\"angus.liu96@gmail.com\"" +
            "}";

    public static final String QUERY_USER_LIST = "{" +
            "\"type\":\"GET_USER_LIST\"," +
            "\"from\":\"angus.liu96@gmail.com\"" +
            "}";

    public static Message signUpMessage(){
        Message message = new Message();
        message.setType(MessageType.SIGN_UP);

        System.out.print("nickname:");
        String nickname = in.nextLine();
        System.out.print("account:");
        String account = in.nextLine();
        System.out.print("password:");
        String password = in.nextLine();
        User user = new User(nickname, account, password);
        message.setUser(user);

        return message;
    }

    public static Message signInMessage(){
        Message message = new Message();
        message.setType(MessageType.SIGN_IN);

        System.out.print("account:");
        String account = in.nextLine();
        System.out.print("password:");
        String password = in.nextLine();
        User user = new User(account, password);
        message.setUser(user);

        return message;
    }

    public static Message privateChatMessage(User user){
        Message message = new Message();
        message.setType(MessageType.PRIVATE_CHAT);

        String from = user.getAccount();
        message.setFrom(from);

        System.out.print("to:");
        String to = in.nextLine();
        message.setTo(to);

        Content content =  new Content();
        content.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        content.setType(ContentType.TEXT);
        System.out.print("info:");
        String info = in.nextLine();
        content.setInfo(info);
        message.setContent(content);

        return message;
    }

    public static Message GroupChatMessage(User user){
        Message message = new Message();
        message.setType(MessageType.GROUP_CHAT);

        String from = user.getAccount();
        message.setFrom(from);

        Content content =  new Content();
        content.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        content.setType(ContentType.TEXT);
        System.out.print("info:");
        String info = in.nextLine();
        content.setInfo(info);
        message.setContent(content);

        return message;
    }

    public static Message GetUserListMessage(User user){
        Message message = new Message();
        message.setType(MessageType.GET_USER_LIST);

        String from = user.getAccount();
        message.setFrom(from);

        return message;
    }
}
