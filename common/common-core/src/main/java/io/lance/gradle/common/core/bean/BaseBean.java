package io.lance.gradle.common.core.bean;

import java.io.Serializable;

/**
 * @desc:
 * @author: lance
 * @time: 2017-09-30 17:18
 */
public class BaseBean implements Serializable {

    /**
     * 请求ip
     */
    private String ip;
    /**
     * 请求url
     */
    private String requestUrl;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
