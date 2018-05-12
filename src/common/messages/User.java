package common.messages;

/**
 * Description:
 * Author: Angus Liu
 * Date: 2018/5/10
 * Version: v1.0
 */
public class User {
    private int id;          // ID

    private String nickname; // 昵称

    private String account;  // 账号

    private String password; // 密码

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(String nickname, String account, String password) {
        this.nickname = nickname;
        this.account = account;
        this.password = password;
    }

    public User(int id, String nickname, String account, String password) {
        this.id = id;
        this.nickname = nickname;
        this.account = account;
        this.password = password;
    }

    public User(CharSequence account, CharSequence password) {
        this(account.toString(), password.toString());
    }

    public String getNickname() {
        return nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
