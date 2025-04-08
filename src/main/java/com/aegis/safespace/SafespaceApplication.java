package com.aegis.safespace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SafespaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SafespaceApplication.class, args);
	}
}
