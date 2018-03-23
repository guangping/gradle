package io.lance.gradle.common.core.spring.disruptor.generic;

import com.lmax.disruptor.EventHandler;

/**
 * 异常处理
 */
public class GenericExceptionEventHandler<T> implements EventHandler<GenericEvent<T>> {

    protected String name;

    public GenericExceptionEventHandler() {
    }

    public GenericExceptionEventHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(GenericEvent<T> event, long sequence, boolean endOfBatch) throws Exception {

        if ("ex".equals(event.get().toString())) {
            throw new RuntimeException("手动异常");
        }
        System.out.println("消费者EventHandler(" + name + ")" + ":消息内容->" + event.get()
                + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }

}
