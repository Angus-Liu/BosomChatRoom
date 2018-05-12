package common.messages;

/**
 * Description: 消息内容对应的实体
 * Author: Angus Liu
 * Date: 2018/5/10
 * Version: v1.0
 */
public class Content {
    private String date;      // 日期时间

    private ContentType type; // 类型

    private String info;      // 信息

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Content{" +
                "date='" + date + '\'' +
                ", type=" + type +
                ", info='" + info + '\'' +
                '}';
    }
}
