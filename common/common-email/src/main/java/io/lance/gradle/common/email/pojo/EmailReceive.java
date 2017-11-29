package io.lance.gradle.common.email.pojo;

import java.io.Serializable;

/**
 * @author Lance.
 * @time: 2017-11-29 16:31
 * @desc: 邮件接收者
 */
public class EmailReceive implements Serializable{

    //名称
    private String userName;

    //邮件
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailReceive{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
