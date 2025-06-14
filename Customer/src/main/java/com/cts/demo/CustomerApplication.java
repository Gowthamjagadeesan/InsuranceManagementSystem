package com.cts.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Marks this class as the main entry point for the Spring Boot application
@SpringBootApplication
@EnableFeignClients
public class CustomerApplication {

	// The main method that launches the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
