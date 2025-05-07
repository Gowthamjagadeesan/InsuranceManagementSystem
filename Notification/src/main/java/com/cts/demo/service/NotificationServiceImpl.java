package com.cts.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.Policy;
import com.cts.demo.entity.Notification;
import com.cts.demo.feignclient.PolicyClient;
import com.cts.demo.repository.NotificationRepository;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository repository;

	@Autowired
	PolicyClient policyClient;
	
	@Override
	public String saveNotification(String message,long customerId, long policyId) {
		Notification notification =new Notification();
		notification.setMessage(message);
		notification.setCustomerId(customerId);
		notification.setPolicyId(policyId);
		repository.save(notification);
		return "Notification saved Successfully";
		
	}
	
	@Override
	public String sendNotification(String message, long customerId, long policyId) {
		Policy policy = policyClient.retrievePolicy(policyId);
		LocalDate validityPeriod = policy.getValidityPeriod();
		LocalDate today = LocalDate.now();
		 long daysBetween = ChronoUnit.DAYS.between(today,validityPeriod);
		 if(daysBetween < 10) {
			 saveNotification(message, customerId, policyId);
			 return "Message saved Succesfully";
		 }
		 else {
			 return "THe validity period is upto mark";
		 }
		
	}


	
}
