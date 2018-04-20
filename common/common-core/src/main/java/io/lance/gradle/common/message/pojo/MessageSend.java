package io.lance.gradle.common.message.pojo;

import io.lance.gradle.common.message.util.MessageTemplateCode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author lance.
 * @time: 2018-04-19 17:52
 * @desc:
 */
public class MessageSend implements Serializable {

    private MessageTemplateCode templateCode;

    private HashMap templateParam;

    public MessageTemplateCode getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(MessageTemplateCode templateCode) {
        this.templateCode = templateCode;
    }

    public HashMap getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(HashMap templateParam) {
        this.templateParam = templateParam;
    }
}
