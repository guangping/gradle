package io.lance.gradle.common.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lance.
 * @time: 2017-11-22 16:43
 * @desc:
 */
@SpringBootApplication(scanBasePackages = {"io.lance.gradle.common.*"})
public class JobMain {

    public static void main(String[] args) {
        System.out.println("start ........");
        SpringApplication.run(JobMain.class, args);
    }
}
