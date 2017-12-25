package io.lance.gradle.common.cache;

import com.alibaba.fastjson.TypeReference;

/**
 * @desc: 缓存规范接口
 * @author: lance
 * @time: 2017-09-21 17:13
 */
public interface ICache {

    /**
     * @param key
     * @param value
     * @desc: 存储
     * @author lance
     * @time: 2017-11-22 14:53:13
     */
    void set(String key, String value);

    /**
     * @desc: 存储
     * @author lance
     * @time: 2017-11-22 14:53:13
     */
    void set(String key, String value, int expire);

    String get(String key);

    void del(String key);

    /**
     * @desc: 增长 默认方式 从0开始每次加-
     * @author lance
     * @time: 2017-11-22 14:49:30
     */
    long incr(String key);

    /**
     * @desc: 增长 可以设定增长量
     * @author lance
     * @time: 2017-11-22 14:49:30
     */
    long incr(String key, long by);

    /**
     * @desc: 获取对象
     * @author: lance
     * @time: 2017-09-21 17:16:24
     */
    <T> T getObj(String key, Class<T> clazz);

    <T> T getObj(String key, TypeReference<T> type);


}
