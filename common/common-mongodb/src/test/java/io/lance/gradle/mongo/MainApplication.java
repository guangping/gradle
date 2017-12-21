package io.lance.gradle.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lance.
 * @time: 2017-12-21 16:05
 * @desc:
 */
@SpringBootApplication(scanBasePackages = {"io.lance.gradle.common.*"})
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("start ........");
        SpringApplication.run(MainApplication.class, args);
    }
}
