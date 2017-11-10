package io.lance.gradle.common.core.spring.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author lance
 * @desc: 容器初始化后执行
 * @time: 2017-11-10 17:06:51
 */
public class ApplicationInitListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("spring 初始化后执行 .....");

        /*获取容器对象*/
        ApplicationContext applicationContext = event.getApplicationContext();


    }
}
