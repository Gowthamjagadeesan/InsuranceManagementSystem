package com.cts.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cts.demo.dto.Customer;

//Declares this interface as a Feign client for communicating with the "CUSTOMER" microservice
//All requests made through this client will be prefixed with "/customer"
@FeignClient(name = "CUSTOMER", path = "/customer")
public interface CustomerClient {

	// Maps to GET /customer/searchById/{cid}
	// Retrieves a Customer by their ID
	@GetMapping("/searchById/{cid}")
	public Customer getCustomer(@PathVariable("cid") long customerID);

	// Maps to PUT /customer/assignPoliciesToCustomer/{pid}/{cid}/{pType}
	// Assigns a policy to a customer by policy ID, customer ID, and policy type
	@PutMapping("/assignPoliciesToCustomer/{pid}/{cid}/{pType}")
	public Customer assignPoliciesToCustomer(@PathVariable("pid") long policyId, @PathVariable("cid") long customerId,
			@PathVariable("pType") String policyType);
}
