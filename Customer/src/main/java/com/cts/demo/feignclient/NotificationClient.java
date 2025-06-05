package com.cts.demo.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//Declares this interface as a Feign client for communicating with the "NOTIFICATION" microservice
//All requests made through this client will be prefixed with "/notify"
@FeignClient(name = "NOTIFICATION", path = "/notify")
public interface NotificationClient {

	// Sends a notification (typically with logic to check policy validity before
	// saving)
	@PostMapping("/send/{message}/{cId}/{pId}/{email}")
	public String sendNotification(@PathVariable("message") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId, @PathVariable("email") String customerMail);

	// Directly saves a notification to the database
	@PostMapping("/noti/{msg}/{cId}/{pId}/{email}")
	public String notify(@PathVariable("msg") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId, @PathVariable("email") String customerMail);
}
