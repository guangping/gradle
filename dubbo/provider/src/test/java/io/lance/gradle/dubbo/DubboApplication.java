package io.lance.gradle.dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lance.
 * @time: 2018-01-30 14:26
 * @desc:
 */
@SpringBootApplication(scanBasePackages = {"io.lance.gradle.dubbo.*"})
@EnableDubboConfiguration
public class DubboApplication {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();

    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class, args);
    }
}
