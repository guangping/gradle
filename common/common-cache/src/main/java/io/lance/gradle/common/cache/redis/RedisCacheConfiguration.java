package io.lance.gradle.common.cache.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Lance.
 * @time: 2017-11-17 17:48
 * @desc:
 */
@Configuration
public class RedisCacheConfiguration {

    private static final Logger logger = LogManager.getLogger();

    private static final String REDIS_HOST = "redis_host";
    private static final String REDIS_PORT = "redis_port";


    @Bean
    public RedissonClient redissonClient() throws IOException {
        logger.info("redis配置初始化....");
        //TODO 获取redis配置信息

        String ip = "127.0.0.1";
        String port = "6379";
        //默认单例模式  可支持集群模式、集群模式、主从模式
        Config config = new Config();//host:port
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress("redis://"+ip + ":" + port);

        RedissonClient client = Redisson.create(config);
        String result = client.getConfig().toJSON().toString();
        logger.info("redis初始化结果:{}", result);
        return client;
    }
}
