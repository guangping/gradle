package io.lance.gradle.common.cache.test;


import io.lance.gradle.common.cache.AppMain;
import io.lance.gradle.common.cache.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Lance.
 * @time: 2017-11-21 15:28
 * @desc:
 */
@SpringBootTest(classes = AppMain.class)
public class RedisTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RedisCache redisCache;


    @Test(invocationCount = 10)
    public void incr() {
        String key = "incr";
        long value = this.redisCache.incr(key);
        System.out.println(value);
    }

    @Test
    public void runIncr(){
        String key="Mac";
        long value = this.redisCache.incr(key,1);
        System.out.println(value);
    }


}
