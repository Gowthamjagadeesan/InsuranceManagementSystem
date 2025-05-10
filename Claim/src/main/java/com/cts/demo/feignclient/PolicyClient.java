package com.cts.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.demo.dto.Policy;

//This annotation marks the interface as a Feign client, which allows it to communicate 
//with another microservice named "POLICY". The 'path' specifies the base URI for all requests.
@FeignClient(name = "POLICY", path = "/policy")
public interface PolicyClient {

	// This annotation maps HTTP GET requests to the method below.
	// It specifies that a GET request to "/policy/retrieveById/{pid}" will invoke
	// this method.
	@GetMapping("/retrieveById/{pid}")

	// This method is used to retrieve a Policy object by its ID.
	// The @PathVariable annotation binds the method parameter 'policyId' to the
	// 'pid' path variable in the URL.
	public Policy retrievePolicy(@PathVariable("pid") long policyId);
}
