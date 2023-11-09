package com.sparta.spartaposts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpartaPostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpartaPostsApplication.class, args);
	}

}
