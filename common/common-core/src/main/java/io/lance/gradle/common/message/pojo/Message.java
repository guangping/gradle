package io.lance.gradle.common.message.pojo;

import java.io.Serializable;

/**
 * @author lance.
 * @time: 2018-04-20 14:20
 * @desc: 消息体
 */
public class Message implements Serializable {

    private String title;

    private String content;

    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
