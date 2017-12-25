package io.lance.gradle.common.web.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Lance.
 * @time: 2017-12-25 15:36
 * @desc:
 */
@Component
@Order(value = 1)
public class InitService implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void run(String... args) throws Exception {
        logger.info("spring boot 初始化时执行.....");
    }
}
