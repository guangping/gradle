package io.lance.gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GradleApplication {

	public static void main(String[] args) {
		System.out.println("start ........");
		SpringApplication.run(GradleApplication.class, args);
	}
}
