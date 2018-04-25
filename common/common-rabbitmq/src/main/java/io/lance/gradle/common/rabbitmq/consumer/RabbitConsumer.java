package io.lance.gradle.common.rabbitmq.consumer;

import io.lance.gradle.common.rabbitmq.util.RabbitQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lance.
 * @time: 2018-04-23 15:40
 * @desc:
 */
@Component
@RabbitListener(queues = RabbitQueue.NOTICE)
public class RabbitConsumer {

    private static final Logger logger = LogManager.getLogger();

    @RabbitHandler //自动确认
    public void process(String str) {
        logger.info("{} -> receive msg:{}", "消费者1", str);
    }

}
