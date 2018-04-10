package io.lance.gradle.disruptor.test;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import io.lance.gradle.common.core.disruptor.generic.GenericEvent;
import io.lance.gradle.common.core.disruptor.generic.GenericEventFactory;
import io.lance.gradle.common.core.disruptor.generic.GenericEventProducer;
import io.lance.gradle.common.core.disruptor.generic.GenericWorkHandler;
import io.lance.gradle.common.core.util.Constants;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;

/**
 * @author Lance.
 * @time: 2018-03-22 17:35
 * @desc:
 */
public class MainTest {


    private Disruptor<GenericEvent<String>> disruptor = null;

    @BeforeTest
    public void setUp() {
        GenericEventFactory<GenericEvent<String>> eventFactory = new GenericEventFactory<GenericEvent<String>>();

        disruptor = new Disruptor(eventFactory, Constants.RING_BUFFER_SIZE,
                Executors.defaultThreadFactory());

        /**可以设置handler执行顺序
         disruptor.handleEventsWith(null).then(null).and(null);
         */

        // disruptor.handleEventsWith(new GenericEventHandler<String>("1"));
        //多个消费者 消息只消费一次
        disruptor.handleEventsWithWorkerPool(new GenericWorkHandler<String>("1"),
                new GenericWorkHandler<>("2"));


        //异常处理
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<GenericEvent<String>>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, GenericEvent<String> event) {
                System.out.println("业务处理异常," + sequence + ",value：" + event.get());

                ex.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                System.out.println("启动时异常");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                System.out.println("关闭时异常");
            }
        });


        disruptor.start();
    }

    @Test
    public void publish() {
        RingBuffer<GenericEvent<String>> ringBuffer = disruptor.getRingBuffer();

        GenericEventProducer<String> producer = new GenericEventProducer(ringBuffer);
        producer.publish("1");
        producer.publish("2");
        producer.publish("3");
        producer.publish("ex");
        producer.publish("5");
        producer.publish("6");


        System.out.println("end.....");
    }


}
