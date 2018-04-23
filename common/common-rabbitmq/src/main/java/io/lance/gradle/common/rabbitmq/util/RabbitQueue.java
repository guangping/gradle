package io.lance.gradle.common.rabbitmq.util;

/**
 * @author lance.
 * @time: 2018-04-23 10:58
 * @desc: 消息队列配置
 */
public interface RabbitQueue {

    String NOTICE = "notice";
    String SYS_LOG = "sys_log";
    String EMAIL = "email";
    String SMS = "sms";
    String SITE = "site";


}
