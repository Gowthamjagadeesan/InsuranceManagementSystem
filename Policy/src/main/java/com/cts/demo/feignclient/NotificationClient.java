package com.cts.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "NOTIFICATION", path = "/notify")
public interface NotificationClient {
	@PostMapping("/send/{msg}/{cId}/{pId}")
	public String sendNotification(@PathVariable("msg") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId);

	@PostMapping("/noti/{msg}/{cId}/{pId}")
	public String notify(@PathVariable("msg") String message, @PathVariable("cId") long customerId,
			@PathVariable("pId") long policyId);
}
