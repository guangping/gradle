package io.lance.gradle.common.rabbitmq.config;

import io.lance.gradle.common.rabbitmq.util.RabbitExchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lance.
 * @time: 2018-04-23 11:28
 * @desc: 交换机配置并绑定queue
 */
@Configuration
public class RabbitExchangeConfig {

    private static final Logger logger = LogManager.getLogger(RabbitExchangeConfig.class);

    @Autowired
    private RabbitAdmin rabbitAdmin;

    /**
     * 不处理路由键。你只需要简单的将队列绑定到交换机上。
     * 一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
     * 很像子网广播，每台子网内的主机都获得了一份复制的消息。Fanout交换机转发消息是最快的。
     *
     * @return
     */
    @Bean
    FanoutExchange contractFanoutExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange(RabbitExchange.CONTRACT_FANOUT);
        rabbitAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }

    /**
     * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
     * 因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”
     * 默认：, durable = true, autoDelete = false
     *
     * @return
     */
  /*  @Bean
    TopicExchange contractTopicExchangeDurable() {
        TopicExchange contractTopicExchange = new TopicExchange(RabbitExchange.CONTRACT_TOPIC);
        rabbitAdmin.declareExchange(contractTopicExchange);
        return contractTopicExchange;
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }*/

    /**
     * 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。
     * 如果一个队列绑定到该交换机上要求路由键 “dog”，则只有被标记为“dog”的消息才被转发，不会转发dog.puppy，也不会转发dog.guard，只会转发dog
     *
     * @return
     */
/*    @Bean
     DirectExchange contractDirectExchange() {
        DirectExchange contractDirectExchange = new DirectExchange(RabbitExchange.CONTRACT_DIRECT);
        rabbitAdmin.declareExchange(contractDirectExchange);
        return contractDirectExchange;
    }*/

    //交换机和队列绑定
    @Bean
    Binding bindingExchangeContract(Queue queue, FanoutExchange exchange) {
        Binding binding = BindingBuilder.bind(queue).to(exchange);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

}
