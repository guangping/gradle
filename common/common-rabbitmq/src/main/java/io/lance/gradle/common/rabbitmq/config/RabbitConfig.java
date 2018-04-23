package io.lance.gradle.common.rabbitmq.config;

import io.lance.gradle.common.core.exception.EbsException;
import io.lance.gradle.common.rabbitmq.util.RabbitQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author lance.
 * @time: 2018-04-23 10:28
 * @desc:
 */
@Configuration
public class RabbitConfig {

    private static final Logger logger = LogManager.getLogger(RabbitConfig.class);


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("");
        factory.setPort(5672);
        factory.setUsername("rabbit");
        factory.setPassword("123456");
        factory.setVirtualHost("/");
        factory.setPublisherConfirms(Boolean.TRUE);
        factory.setPublisherReturns(Boolean.TRUE);


        logger.info("init connection factory");

        return factory;
    }

    /**
     * rabbitAdmin代理类
     *
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        return rabbitAdmin;
    }

    /**
     * 消息模板类
     * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
     *
     * @author lance
     * @date 2018-04-23 11:21:12
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());

        //rabbitTemplate.setMandatory(true);//返回消息必须设置为true
        //rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //发布确认  消息发送到queue时就执行
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                logger.debug(correlationData + "//////");
                if (!b) {
                    logger.debug("发送到queue失败");
                    throw new EbsException("rabbit send error " + s);
                }
            }
        });
        //消息发送失败监听
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                logger.info(s);
            }
        });

        return rabbitTemplate;
    }


    @Bean
    public Queue queue() {
        Queue queue = new Queue(RabbitQueue.NOTICE, true,false,false);//queue 持久化

        return queue;
    }


}
