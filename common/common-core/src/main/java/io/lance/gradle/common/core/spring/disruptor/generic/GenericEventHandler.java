package io.lance.gradle.common.core.spring.disruptor.generic;

import com.lmax.disruptor.EventHandler;

/**
 * 定义事件处理的具体实现
 *
 * @author lance
 * @date 2018-03-22 17:49:46
 */
public class GenericEventHandler<T> implements EventHandler<GenericEvent<T>> {

    protected String name;

    public GenericEventHandler() {
    }

    public GenericEventHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(GenericEvent<T> event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者EventHandler(" + name + ")" + ":" + event.get()
                + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }

}
