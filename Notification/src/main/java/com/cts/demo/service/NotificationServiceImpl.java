package com.cts.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.Policy;
import com.cts.demo.entity.Notification;
import com.cts.demo.feignclient.PolicyClient;
import com.cts.demo.repository.NotificationRepository;

//Marks this class as a Spring service component with the name "notificationService"
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

	// Logger for logging information and debugging
	Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	// Injects the NotificationRepository to interact with the database
	@Autowired
	NotificationRepository repository;

	// Injects the Feign client to retrieve policy details from the POLICY
	// microservice
	@Autowired
	PolicyClient policyClient;

	// Saves a notification to the database
	@Override
	public String saveNotification(String message, long customerId, long policyId) {
		logger.info("Saving notification - Message: '{}', Customer ID: {}, Policy ID: {}", message, customerId,
				policyId);
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setCustomerId(customerId);
		notification.setPolicyId(policyId);
		repository.save(notification);
		logger.info("Notification saved successfully for Customer ID: {}", customerId);
		return "Notification saved Successfully";
	}

	// Sends a notification only if the policy is nearing expiry (less than 10 days
	// left)
	@Override
	public String sendNotification(String message, long customerId, long policyId) {
		logger.info("Sending notification - Message: '{}', Customer ID: {}, Policy ID: {}", message, customerId,
				policyId);

		// Retrieve policy details using Feign client
		Policy policy = policyClient.retrievePolicy(policyId);
		LocalDate validityPeriod = policy.getValidityPeriod();
		LocalDate today = LocalDate.now();

		// Calculate days remaining until policy expiry
		long daysBetween = ChronoUnit.DAYS.between(today, validityPeriod);
		logger.debug("Days until policy expiry: {}", daysBetween);

		// Save notification only if policy is expiring soon
		if (daysBetween < 10) {
			logger.info("Policy is nearing expiry. Saving notification.");
			saveNotification(message, customerId, policyId);
			return "Message saved Successfully";
		} else {
			logger.info("Policy validity is sufficient. No notification saved.");
			return "The validity period is up to mark";
		}
	}
}
