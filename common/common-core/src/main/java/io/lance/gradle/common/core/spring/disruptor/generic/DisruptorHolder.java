package io.lance.gradle.common.core.spring.disruptor.generic;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


public class DisruptorHolder<T> {

    private int bufferSize = 1024;

    private GenericEventFactory<T> eventFactory = new GenericEventFactory<T>();

    private ThreadFactory threadFactory = Executors.defaultThreadFactory();



}
