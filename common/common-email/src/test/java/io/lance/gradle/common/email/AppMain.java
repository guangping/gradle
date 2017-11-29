package io.lance.gradle.common.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lance.
 * @time: 2017-11-29 16:52
 * @desc:
 */
@SpringBootApplication(scanBasePackages = {"io.lance.gradle.common.*"})
public class AppMain {

    public static void main(String[] args) {
        System.out.println("start ........");
        SpringApplication.run(AppMain.class, args);
    }
}
