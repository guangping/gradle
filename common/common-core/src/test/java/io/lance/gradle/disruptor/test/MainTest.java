package io.lance.gradle.disruptor.test;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import io.lance.gradle.common.core.disruptor.generic.GenericEvent;
import io.lance.gradle.common.core.disruptor.generic.GenericEventFactory;
import io.lance.gradle.common.core.disruptor.generic.GenericEventHandler;
import io.lance.gradle.common.core.disruptor.generic.GenericEventProducer;
import io.lance.gradle.common.core.disruptor.generic.GenericWorkHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;

/**
 * @author Lance.
 * @time: 2018-03-22 17:35
 * @desc:
 */
public class MainTest {

    private static final int bufferSize = 8;

    private Disruptor<GenericEvent<String>> disruptor = null;

    @BeforeTest
    public void setUp() {
        GenericEventFactory<GenericEvent<String>> eventFactory = new GenericEventFactory<GenericEvent<String>>();

        disruptor = new Disruptor(eventFactory, bufferSize,
                Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new GenericEventHandler<String>("1"));
        disruptor.handleEventsWithWorkerPool(new GenericWorkHandler<String>("2"));

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
