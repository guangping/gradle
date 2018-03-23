package io.lance.gradle.disruptor.test;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * @author Lance.
 * @time: 2018-03-22 17:31
 * @desc:
 */
public class MainTestJava8 {

    private static final int ringBufferSize = 8;

    public static void main(String[] args) {

        //only one disruptor in application
        Disruptor<StringEvent> disruptor = new Disruptor<StringEvent>(
                StringEvent::new, ringBufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI,
                new BlockingWaitStrategy());

        disruptor.handleEventsWith(
                ((event, sequence, endOfBatch) -> {
                    System.out.println("handler Event:" + event.value);
                })
        );
        disruptor.start();

        disruptor.getRingBuffer().publishEvent((event, sequence, arg) -> event.value = arg, "消息1");

        System.out.println("main end...");

    }


    static class StringEvent {
        public String value;
    }
}
