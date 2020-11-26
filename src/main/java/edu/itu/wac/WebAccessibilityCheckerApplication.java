package edu.itu.wac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"edu.itu.wac"})
public class WebAccessibilityCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAccessibilityCheckerApplication.class, args);
	}

}
