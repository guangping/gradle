package io.lance.gradle.common.mq.service;

/**
 * @author Lance.
 * @time: 2017-11-29 16:00
 * @desc:
 */
public interface MqService {

    /**
     * @param name        队列名称
     * @param messageBody 消息内容
     * @desc: 消息发送
     * @author lance
     * @time: 2017-11-29 16:00:45
     */
    String send(String name, String messageBody);

}
