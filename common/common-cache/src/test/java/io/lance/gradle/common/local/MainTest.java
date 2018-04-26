package io.lance.gradle.common.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author lance.
 * @time: 2018-04-25 17:57
 * @desc:
 */
public class MainTest {

    private Cache<String, Object> manualCache = null;

    @BeforeTest
    public void setUp() {
        manualCache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
    }

    @Test
    public void run() {
        String key = "name1";
        // 根据key查询一个缓存，如果没有返回NULL
        Object graph = manualCache.getIfPresent(key);
        System.out.println(graph);

        // 根据Key查询一个缓存，如果没有调用createExpensiveGraph方法，并将返回值保存到缓存。
        // 如果该方法返回Null则manualCache.get返回null，如果该方法抛出异常则manualCache.get抛出异常
       // graph = manualCache.get(key, k -> createExpensiveGraph(k));
        // 将一个值放入缓存，如果以前有值就覆盖以前的值
       // manualCache.put(key, graph);
        // 删除一个缓存
       // manualCache.invalidate(key);
    }

}
