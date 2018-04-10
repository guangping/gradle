package io.lance.gradle.disruptor.test;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;

/**
 * @author Lance.
 * @time: 2018-03-22 17:31
 * @desc:
 */
public class MainTestJava8 {

    private static final int ringBufferSize = 8;

    private Disruptor<StringEvent> disruptor = null;

    @BeforeTest
    public void setUp() {
        disruptor = new Disruptor<StringEvent>(
                StringEvent::new, ringBufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI,
                new YieldingWaitStrategy());

        disruptor.handleEventsWith(
                ((event, sequence, endOfBatch) -> {
                    System.out.println("handler Event:" + event.value);
                })
        );
        disruptor.handleEventsWithWorkerPool(event -> {
            System.out.println("handler Event:" + event.value);
        });
        disruptor.start();
    }

    @Test
    public void run() {
        disruptor.getRingBuffer().publishEvent((event, sequence, arg) -> event.value = arg, "消息1");

        System.out.println("main end...");
    }


    static class StringEvent {
        public String value;
    }
}
