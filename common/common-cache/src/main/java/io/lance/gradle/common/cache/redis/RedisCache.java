package io.lance.gradle.common.cache.redis;

import com.alibaba.fastjson.TypeReference;
import io.lance.gradle.common.cache.ICache;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Lance.
 * @time: 2017-11-17 17:47
 * @desc:
 */
@Service
public class RedisCache implements ICache {

    @Autowired
    private RedissonClient client;


    @Override
    public void set(String key, String value) {
        RBucket<String> bucket = this.client.getBucket(key);
        bucket.setAsync(value);
    }

    @Override
    public void set(String key, String value, int expire) {
        RBucket<String> bucket = this.client.getBucket(key);
        bucket.setAsync(value, expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public String get(String key) {
        RBucket<String> bucket = this.client.getBucket(key);
        return bucket.get();
    }

    @Override
    public void del(String key) {
        RBucket<String> bucket = this.client.getBucket(key);
        bucket.deleteAsync();
    }

    @Override
    public long incr(String key) {
        RAtomicLong atomicLong = this.client.getAtomicLong(key);
        long value = atomicLong.get();
        return value;
    }

    @Override
    public long incr(String key, long by) {
        RAtomicLong atomicLong = this.client.getAtomicLong(key);
        long value = atomicLong.getAndAdd(by);
        return value;
    }

    @Override
    public long incr(String key, long by, long def) {
        return 0;
    }

    @Override
    public <T> T getObj(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> T getObj(String key, TypeReference<T> type) {
        return null;
    }

    @Override
    public void set(String key, Object value) {

    }

    @Override
    public void set(String key, Object value, int expire) {

    }
}
