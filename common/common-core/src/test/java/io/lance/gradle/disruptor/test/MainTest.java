package io.lance.gradle.disruptor.test;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import io.lance.gradle.common.core.disruptor.generic.GenericEvent;
import io.lance.gradle.common.core.disruptor.generic.GenericEventFactory;
import io.lance.gradle.common.core.disruptor.generic.GenericEventHandler;
import io.lance.gradle.common.core.disruptor.generic.GenericEventProducer;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Lance.
 * @time: 2018-03-22 17:35
 * @desc:
 */
public class MainTest {

    private static final int bufferSize = 8;

    public static void main(String[] args) {
        GenericEventFactory<GenericEvent<String>> eventFactory = new GenericEventFactory<GenericEvent<String>>();

        Disruptor<GenericEvent<String>> disruptor = new Disruptor(eventFactory, bufferSize,
                Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new GenericEventHandler<String>("1"));

        disruptor.start();

        RingBuffer<GenericEvent<String>> ringBuffer = disruptor.getRingBuffer();

        GenericEventProducer<String> producer = new GenericEventProducer(ringBuffer);
        //producer.publish("ssss");


        ExecutorService es = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            es.submit(new Runnable() {
                public void run() {
                    for (long l = 0; l < 20; l++) {
                        producer.publish(UUID.randomUUID().toString());
                    }
                }
            });
        }
        System.out.println("end.....");

    }


}
