package io.lance.gradle.common.cache;

/**
 * @author lance.
 * @time: 2018-05-11 18:05
 * @desc:
 */
public interface CacheLoader<K, V> {

    V load(K key);
}
