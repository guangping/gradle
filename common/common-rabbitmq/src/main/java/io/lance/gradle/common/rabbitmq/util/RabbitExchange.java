package io.lance.gradle.common.rabbitmq.util;

/**
 * @author lance.
 * @time: 2018-04-23 11:25
 * @desc: exchange交换机配置
 */
public interface RabbitExchange {
    String CONTRACT_FANOUT = "CONTRACT_FANOUT";

    String CONTRACT_TOPIC = "CONTRACT_TOPIC";

    String CONTRACT_DIRECT = "CONTRACT_DIRECT";
}
