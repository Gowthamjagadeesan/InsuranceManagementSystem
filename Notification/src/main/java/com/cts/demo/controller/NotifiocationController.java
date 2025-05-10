package com.cts.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.demo.service.NotificationService;

//Marks this class as a REST controller that handles HTTP requests and returns JSON responses
@RestController

//Base URL path for all endpoints in this controller
@RequestMapping("/notify")
public class NotifiocationController {

	// Logger for logging request and process information
	private static final Logger logger = LoggerFactory.getLogger(NotifiocationController.class);

	// Injects the NotificationService to handle business logic
	@Autowired
	NotificationService service;

	// Endpoint to save a notification
	// Example: POST /notify/noti/Your%20policy%20is%20approved/101/202
	@PostMapping("/noti/{msg}/{cId}/{pId}")
	public String notify(@PathVariable("msg") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId) {
		logger.info("Saving notification - Message: '{}', Customer ID: {}, Policy ID: {}", message, customerId,
				policyId);
		return service.saveNotification(message, customerId, policyId);
	}

	// Endpoint to send a notification (could include additional logic like
	// email/SMS in the service layer)
	// Example: POST /notify/send/Your%20claim%20is%20approved/101/202
	@PostMapping("/send/{message}/{cId}/{pId}")
	public String sendNotification(@PathVariable("message") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId) {
		logger.info("Sending notification - Message: '{}', Customer ID: {}, Policy ID: {}", message, customerId,
				policyId);
		return service.sendNotification(message, customerId, policyId);
	}
}
