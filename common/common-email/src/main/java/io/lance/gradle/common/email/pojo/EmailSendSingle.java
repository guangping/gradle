package io.lance.gradle.common.email.pojo;

import java.io.Serializable;

/**
 * @author lance.
 * @time: 2018-04-19 16:51
 * @desc:
 */
public class EmailSendSingle implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 收件人名称
     */
    private String receiveUserName;

    /**
     * 收件人地址
     */
    private String receiveEmail;

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

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    @Override
    public String toString() {
        return "EmailSendSingle{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", receiveUserName='" + receiveUserName + '\'' +
                ", receiveEmail='" + receiveEmail + '\'' +
                '}';
    }
}
