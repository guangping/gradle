package io.lance.gradle.common.email.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Lance.
 * @time: 2017-11-29 16:17
 * @desc: 邮件配置
 */
@Component
@ConfigurationProperties(prefix = "email")
public class EmailConfig {

    //地址
    private String host;

    //端口
    private int port = 25;

    //用户名
    private String userName;

    //密码
    private String password;

    //发件方
    private String from;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "EmailConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
