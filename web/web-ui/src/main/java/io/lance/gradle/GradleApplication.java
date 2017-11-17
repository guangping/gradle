package io.lance.gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication(scanBasePackages = {"io.lance.gradle.common.*","io.lance.gradle.api.*"})
public class GradleApplication {

	public static void main(String[] args) {
		System.out.println("start ........");
		SpringApplication.run(GradleApplication.class, args);
	}
}
