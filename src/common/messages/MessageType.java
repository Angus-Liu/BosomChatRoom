package common.messages;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/11
 * Version: v1.0
 */
public enum MessageType {
    SIGN_UP("注册消息"),

    SIGN_UP_RESULT("注册结果消息"),

    SIGN_IN("登录消息"),

    SIGN_IN_RESULT("登录结果消息"),

    SIGN_OUT("登出消息"),

    HANDLE_RESULT("处理结果消息"),

    GET_USER_INFO("获取用户信息"),

    USER_INFO("用户信息消息"),

    GET_USER_LIST("获取用户列表消息"),

    USER_LIST("用户列表消息"),

    PRIVATE_CHAT("私聊消息"),

    GROUP_CHAT("群聊消息"),

    NOTIFICATION("通知消息");

    public String value;

    MessageType(String value) {
        this.value = value;
    }
}