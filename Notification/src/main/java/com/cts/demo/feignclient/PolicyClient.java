package com.cts.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.demo.dto.Policy;

//Declares this interface as a Feign client for communicating with the "POLICY" microservice
//The base path for all requests made through this client is "/policy"
@FeignClient(name = "POLICY", path = "/policy")
public interface PolicyClient {

	// Maps an HTTP GET request to the method below
	// This will call the endpoint: GET /policy/retrieveById/{pid}
	@GetMapping("/retrieveById/{pid}")

	// Method to retrieve a Policy object by its ID
	// The @PathVariable binds the method parameter to the URL path variable
	public Policy retrievePolicy(@PathVariable("pid") long policyId);
}
