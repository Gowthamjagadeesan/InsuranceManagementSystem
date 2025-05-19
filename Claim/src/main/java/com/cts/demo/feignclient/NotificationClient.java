package com.cts.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//Declares this interface as a Feign client for the "NOTIFICATION" service
//The base path for all endpoints is "/notify"
@FeignClient(name = "NOTIFICATION", path = "/notify")
public interface NotificationClient {

//Sends a notification message to a customer for a specific policy
	@PostMapping("/send/{message}/{cId}/{pId}/{email}")
	public String sendNotification(@PathVariable("message") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId, @PathVariable("email") String customerMail);

	// Directly saves a notification to the database
	@PostMapping("/noti/{msg}/{cId}/{pId}/{email}")
	public String notify(@PathVariable("msg") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId, @PathVariable("email") String customerMail);
}
