package com.cts.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Marks this class as the main entry point for the Spring Boot application
@SpringBootApplication

//Enables Feign clients in the application, allowing it to communicate with other microservices via HTTP
@EnableFeignClients
public class NotificationApplication {

	// The main method that launches the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}
}
