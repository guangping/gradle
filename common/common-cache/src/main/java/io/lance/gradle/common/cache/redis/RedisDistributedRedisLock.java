package io.lance.gradle.common.cache.redis;

import io.lance.gradle.common.cache.lock.DistributedRedisLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Lance.
 * @time: 2017-11-22 15:12
 * @desc: redis 分布式锁
 */
@Service
public class RedisDistributedRedisLock implements DistributedRedisLock {
    private static final String LOCK_TITLE = "redisLock_";

    @Autowired
    private RedissonClient client;


    @Override
    public void acquire(String lockName) {
        String key = get(lockName);
        RLock lock = this.client.getLock(key);
        //lock提供带timeout参数，timeout结束强制解锁，防止死锁
        lock.lock(2, TimeUnit.SECONDS);
    }

    @Override
    public void release(String lockName) {
        String key = get(lockName);
        RLock lock = this.client.getLock(key);
        lock.unlock();
    }

    private String get(String lockName) {
        String key = LOCK_TITLE + lockName;

        return key;
    }
}
