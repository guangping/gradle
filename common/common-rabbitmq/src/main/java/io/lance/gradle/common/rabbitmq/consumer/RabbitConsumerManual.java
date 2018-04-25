package io.lance.gradle.common.rabbitmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import io.lance.gradle.common.rabbitmq.util.RabbitQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lance.
 * @time: 2018-04-23 16:18
 * @desc: 对于手动确认的消息处理
 */
@Component
public class RabbitConsumerManual {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        container.addQueueNames(RabbitQueue.NOTICE);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                //channel.basicConsume(message.getMessageProperties().getConsumerQueue(), false, null);
                channel.basicQos(1);//保证在接收端一个消息没有处理完时不会接收另一个消息，即接收端发送了ack后才会接收下一个消息

                // 监听队列，手动返回完成
                byte[] body = message.getBody();
                String str = new String(body);
                logger.info("{} -> receive msg : {}", "消费者2",str);
                String id = JSONObject.parseObject(str).getString("id");

                //确认消息成功消费 false只确认当前一个消息收到，true确认所有consumer获得的消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

                //ack返回false，并重新回到队列，api里面解释得很清楚
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                //拒绝消息
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                //重新发送消息到队尾
                /* channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
                    message.getMessageProperties().getReceivedRoutingKey(), MessageProperties.PERSISTENT_TEXT_PLAIN,
                    JSON.toJSONBytes(new Object()));*/
            }
        });
        return container;
    }
}
