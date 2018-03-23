package io.lance.gradle.disruptor.test;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import io.lance.gradle.common.core.spring.disruptor.generic.GenericEvent;
import io.lance.gradle.common.core.spring.disruptor.generic.GenericEventFactory;
import io.lance.gradle.common.core.spring.disruptor.generic.GenericEventHandler;
import io.lance.gradle.common.core.spring.disruptor.generic.GenericEventProducer;

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

        disruptor.handleEventsWith(new GenericEventHandler("1"));

        disruptor.start();

        RingBuffer<GenericEvent<String>> ringBuffer=disruptor.getRingBuffer();

        GenericEventProducer<String> producer=new GenericEventProducer(ringBuffer);
        producer.publish("ssss");




    }


}
