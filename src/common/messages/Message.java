package common.messages;

import java.util.List;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/10
 * Version: v1.0
 */
public class Message {

    // 类型
    private MessageType type;
    // 处理结果
    private Boolean result;
    // 原因
    private String reason;
    // 单用户
    private User user;
    // 发送方
    private String from;
    // 接收方
    private String to;
    // 内容
    private Content content;
    // 用户列表
    private List<User> userList;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
