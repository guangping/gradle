package io.lance.gradle.common.cache;

import com.alibaba.fastjson.TypeReference;

/**
 * @desc: 缓存规范接口
 * @author: lance
 * @time: 2017-09-21 17:13
 */
public interface ICache {

    void set(String key, String value);

    void set(String key, String value, int expire);

    String get(String key);

    void del(String key);

    /**
     * @desc: 增长
     * @author: lance
     * @time: 2017-09-21 17:15:36
     */
    long incr(String key);

    long incr(String key, long by);

    long incr(String key, long by, long def);

    /**
     * @desc: 获取对象
     * @author: lance
     * @time: 2017-09-21 17:16:24
     */
    <T> T getObj(String key, Class<T> clazz);

    <T> T getObj(String key, TypeReference<T> type);

    /**
     * @desc: 设置对象
     * @author: lance
     * @time: 2017-09-21 17:17:36
     */
    void set(String key, Object value);

    void set(String key, Object value, int expire);


}
