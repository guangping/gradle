package io.lance.gradle.common.cache.lock;

/**
 * @author Lance.
 * @time: 2017-11-22 15:09
 * @desc: 分布式锁
 */
public interface DistributedRedisLock {

    /**
     * 获取锁
     *
     * @param lockName
     * @author lance
     * @time: 2017-11-22 15:11:19
     */
    void acquire(String lockName);

    /**
     * 释放锁
     *
     * @param lockName
     * @author lance
     * @time: 2017-11-22 15:11:45
     */
    void release(String lockName);
}
