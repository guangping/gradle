package io.lance.gradle.common.file.service.sftp.vo;

import java.io.Serializable;

/**
 * @author Lance.
 * @time: 2018-01-23 14:16
 * @desc: 配置信息
 */
public class SFtpConfig implements Serializable {

    private String host;

    private int port = 22;

    private String userName;

    private String password;

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

    @Override
    public String toString() {
        return "SFtpConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
