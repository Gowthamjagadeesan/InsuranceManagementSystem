package com.cts.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.Policy;
import com.cts.demo.entity.Notification;
import com.cts.demo.feignclient.PolicyClient;
import com.cts.demo.repository.NotificationRepository;

/**
 * Service implementation for handling notification-related business logic.
 */
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

	Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	NotificationRepository repository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	PolicyClient policyClient;

	/**
	 * Saves a notification to the database.
	 *
	 * @param message    the notification message
	 * @param customerId the ID of the customer
	 * @param policyId   the ID of the policy
	 * @return confirmation message
	 */
	@Override
	public String saveNotification(String message, long customerId, long policyId, String email) {
		logger.info("Saving notification - Message: '{}', Customer ID: {}, Policy ID: {}", message, customerId,
				policyId);
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setCustomerId(customerId);
		notification.setPolicyId(policyId);
		repository.save(notification);
		sendMail(email, message, email);
		logger.info("Notification saved successfully for Customer ID: {}", customerId);
		return "Notification saved Successfully";
	}

	/**
	 * Sends a notification only if the associated policy is nearing expiry (less
	 * than 10 days left).
	 * 
	 * @param message    the notification message
	 * @param customerId the ID of the customer
	 * @param policyId   the ID of the policy
	 * @return result message indicating whether the notification was saved
	 */
	@Override
	public String sendNotification(String message, long customerId, long policyId, String mail) {
		logger.info("Sending notification - Message: '{}', Customer ID: {}, Policy ID: {}", message, customerId,
				policyId);

		Policy policy = policyClient.retrievePolicy(policyId);
		LocalDate validityPeriod = policy.getValidityPeriod();
		LocalDate today = LocalDate.now();

		long daysBetween = ChronoUnit.DAYS.between(today, validityPeriod);
		logger.debug("Days until policy expiry: {}", daysBetween);

		if (daysBetween < 10) {
			logger.info("Policy is nearing expiry. Saving notification.");
			saveNotification(message, customerId, policyId, mail);
			return "Message saved Successfully";
		} else {
			logger.info("Policy validity is sufficient. No notification saved.");
			return "The validity period is up to mark";
		}
	}

	@Override
	public void sendMail(String toMail, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("regarding your Insurance policy");
		message.setText(subject);
		message.setTo(toMail);
		mailSender.send(message);

	}
}
