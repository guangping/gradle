package io.lance.gradle.common.core.disruptor.generic;

import com.lmax.disruptor.EventFactory;


/**
 * 定义事件工厂
 *
 * @author lance
 * @date 2018-03-22 17:54:07
 */
public class GenericEventFactory<T> implements EventFactory<GenericEvent<T>> {

    @Override
    public GenericEvent<T> newInstance() {
        return new GenericEvent();
    }
}
