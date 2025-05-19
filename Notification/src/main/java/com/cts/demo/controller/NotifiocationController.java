package com.cts.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.demo.service.NotificationService;

/**
 * REST controller for handling notification-related HTTP requests.
 */
@RestController
@RequestMapping("/notify")
public class NotifiocationController {

	private static final Logger logger = LoggerFactory.getLogger(NotifiocationController.class);

	@Autowired
	NotificationService service;

	/**
	 * Saves a notification to the system.
	 * 
	 * Example: POST /notify/noti/Your%20policy%20is%20approved/101/202
	 *
	 * @param message    the notification message
	 * @param customerId the ID of the customer
	 * @param policyId   the ID of the policy
	 * @return confirmation message
	 */
	@PostMapping("/noti/{msg}/{cId}/{pId}/{email}")
	public String notify(@PathVariable("msg") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId, @PathVariable("email") String customerMail) {
		logger.info("Saving notification - Message: '{}', Customer ID: {}, Policy ID: {}", message, customerId,
				policyId);
		return service.saveNotification(message, customerId, policyId, customerMail);
	}

	/**
	 * Sends a notification to the customer. This could include additional logic
	 * like email or SMS in the service layer.
	 * 
	 * Example: POST /notify/send/Your%20claim%20is%20approved/101/202
	 *
	 * @param message    the notification message
	 * @param customerId the ID of the customer
	 * @param policyId   the ID of the policy
	 * @return confirmation message
	 */
	@PostMapping("/send/{message}/{cId}/{pId}/{email}")
	public String sendNotification(@PathVariable("message") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId, @PathVariable("email") String customerMail) {
		logger.info("Sending notification - Message: '{}', Customer ID: {}, Policy ID: {}", message, customerId,
				policyId);
		return service.sendNotification(message, customerId, policyId, customerMail);
	}
}
