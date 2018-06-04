package io.lance.gradle.common.cache.memcached;

import com.google.code.yanf4j.core.impl.StandardSocketOption;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author lance.
 * @time: 2018-05-04 11:15
 * @desc:
 */
@Configuration
public class CacheConfig {

    private static final Logger logger = LogManager.getLogger();


    @Bean
    public MemcachedClient memcachedClient() throws IOException {
        MemcachedClient memcachedClient = null;

        String host = "127.0.0.1", port = "11211";
        if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(port)) {
            MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(host + ":" + port));
            builder.setConnectionPoolSize(3);
            builder.setCommandFactory(new BinaryCommandFactory());
            builder.setSocketOption(StandardSocketOption.TCP_NODELAY, false);
            builder.getConfiguration().setSessionIdleTimeout(10000);
            memcachedClient = builder.build();
            memcachedClient.setMergeFactor(50);
            memcachedClient.setOptimizeMergeBuffer(false);
            memcachedClient.setOpTimeout(5000L);
        }
        logger.info("XMemcached 初始化:{}", memcachedClient);
        return memcachedClient;
    }

}
