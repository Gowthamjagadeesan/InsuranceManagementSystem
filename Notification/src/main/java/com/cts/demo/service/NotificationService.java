package com.cts.demo.service;

//Service interface for handling notification-related operations
public interface NotificationService {

	// Saves a notification to the database
	// Parameters: message content, customer ID, and policy ID
	public abstract String saveNotification(String message, long customerId, long policyId, String mail);

	// Sends a notification (could include logic like sending an email, SMS, or push
	// notification)
	// Parameters: message content, customer ID, and policy ID
	public abstract String sendNotification(String message, long customerId, long policyId, String mail);

	public abstract void sendMail(String toMail, String subject, String body);
}
