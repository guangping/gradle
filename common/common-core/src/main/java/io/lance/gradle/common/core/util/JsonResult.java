package io.lance.gradle.common.core.util;

import java.io.Serializable;

/**
 * Author: Lance.
 * Date: 2017-09-13 15:18
 * Desc: 统一异常bean
 */
public class JsonResult<T> implements Serializable {

    /**
     * 错误编码
     */
    private Integer code;
    /**
     * 成功 1 失败 0
     */
    private String success = Constants.NO;
    /**
     * 提示信息
     **/
    private String message;
    /**
     * 数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
