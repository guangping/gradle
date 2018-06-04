package io.lance.gradle.common.cache.memcached;

import com.alibaba.fastjson.TypeReference;
import io.lance.gradle.common.cache.ICache;
import io.lance.gradle.common.core.util.JsonUtils;
import io.lance.gradle.common.core.util.TimeConstants;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lance.
 * @time: 2018-06-04 15:45
 * @desc:
 */
@Service
public class MemcachedService implements ICache {

    @Autowired
    private MemcachedClient memcachedClient;

    public void set(String key, String value) {
        set(key, value, TimeConstants.TIME_30M_EXPIRE);
    }

    public void set(String key, String value, int seconds) {
        try {
            memcachedClient.set(key, seconds, value);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        try {
            Object value = memcachedClient.get(key);
            return ObjectUtils.toString(value);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long incr(String key, long by) {
        long value = 0;
        try {
            value = memcachedClient.incr(key, by);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public <T> T getObj(String key, Class<T> clazz) {
        if (null == clazz) {
            return null;
        }
        String value = this.get(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        String className = clazz.getName();
        if (className.equals("java.util.List")) {
            return JsonUtils.parseObject(value, new TypeReference<T>() {
            });
        } else if (className.equals("java.lang.String")) {
            return (T) value;
        } else {
            return JsonUtils.parseObject(value, clazz);
        }
    }

    @Override
    public <T> T getObj(String key, TypeReference<T> type) {
        if (null == type) {
            return null;
        }

        String value = this.get(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return JsonUtils.parseObject(value, type);
    }


    public long decr(String key, long by) {
        long value = 0;
        try {
            value = memcachedClient.decr(key, by);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void reset(String key, int seconds) {
        try {
            Object value = memcachedClient.get(key);
            if (null != value) {
                memcachedClient.replace(key, seconds, value);
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    public void del(String key) {
        try {
            memcachedClient.delete(key);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long incr(String key) {
        return incr(key, 1, 1);
    }

    public long incr(String key, long by, long def) {
        long value = 0;
        try {
            value = memcachedClient.incr(key, by, def);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void disConnect() {
        try {
            memcachedClient.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
