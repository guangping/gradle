package io.lance.gradle.common.core.disruptor.generic;

import com.lmax.disruptor.WorkHandler;


public class GenericWorkHandler<T> implements WorkHandler<GenericEvent<T>> {

    private String name;

    public GenericWorkHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(GenericEvent<T> event) throws Exception {
        System.out.println("消费者workHandler(" + name + ")" + ":" + event.get());
    }
}
