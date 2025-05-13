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
	@PostMapping("/send/{msg}/{cId}/{pId}")
	public String sendNotification(@PathVariable("msg") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId);

	// Directly saves a notification to the database
	@PostMapping("/noti/{msg}/{cId}/{pId}")
	public String notify(@PathVariable("msg") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId);
}
