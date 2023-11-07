package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EntityScan("com")
@EnableJpaRepositories("com")
@ComponentScan("com")
@EnableEurekaClient
@OpenAPIDefinition
public class DiscountMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscountMsApplication.class, args);
	}

}
