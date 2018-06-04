package io.lance.gradle.common.cache.test;

/**
 * @author lance.
 * @time: 2018-06-04 16:31
 * @desc:
 */
public interface CacheCallable<T> {

    T load();
}
