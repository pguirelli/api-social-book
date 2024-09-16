package com.kyron.automation.socialbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookReviewManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReviewManagementApiApplication.class, args);
	}

}
