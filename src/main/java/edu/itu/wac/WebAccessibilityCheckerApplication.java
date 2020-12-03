package edu.itu.wac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication(scanBasePackages = {"edu.itu.wac"})
@EnableMongoAuditing
public class WebAccessibilityCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAccessibilityCheckerApplication.class, args);
	}

}
