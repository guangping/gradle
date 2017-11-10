package io.lance.gradle.common.core.spring.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @desc: 线程池配置
 * @author: lance
 * @time: 2017-10-24 13:52
 */
@Configuration
public class TaskConfiguration {

    private static final Logger logger = LogManager.getLogger();

    /**
     * @desc: 自定义线程池
     * @author: lance
     * @time: 2017-09-15 10:35:59
     */
    @Bean
    public Executor taskExecutor() {
        logger.info("线程池配置 start ......");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(1024);
        executor.setThreadNamePrefix("executor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
