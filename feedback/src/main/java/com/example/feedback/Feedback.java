package com.example.feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@ComponentScan("com")
@EnableJpaRepositories("com.repository")
@EntityScan("com.entity")
@OpenAPIDefinition
public class Feedback {

	public static void main(String[] args) {
		SpringApplication.run(Feedback.class, args);
	}

}
