package io.lance.gradle.common.core.spring.disruptor.generic;


/**
 * 定义事件
 *
 * @author lance
 * @date 2018-03-22 17:53:38
 */
public class GenericEvent<T> {

    private T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
