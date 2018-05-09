package io.lance.gradle.common.job.elastic.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lance.
 * @time: 2018-05-08 17:33
 * @desc: 注册中心配置 用于注册和协调作业分布式行为的组件，目前仅支持Zookeeper
 */
@Configuration
public class RegistryCenterConfig {

    private static final Logger logger = LogManager.getLogger();


    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter() {
        String serverList = "127.0.0.1:2181", namespace = "lance-job";
        ZookeeperConfiguration configuration = new ZookeeperConfiguration(serverList, namespace);
        configuration.setMaxRetries(3);
        configuration.setBaseSleepTimeMilliseconds(1000);
        configuration.setMaxSleepTimeMilliseconds(3000);
        ZookeeperRegistryCenter registryCenter = new ZookeeperRegistryCenter(configuration);

        logger.info("初始化注册中心....");

        return registryCenter;
    }

}
