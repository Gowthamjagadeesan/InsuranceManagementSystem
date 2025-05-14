package com.cts.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.demo.dto.Agent;
import com.cts.demo.dto.Customer;
import com.cts.demo.exception.PolicyNotFoundException;
import com.cts.demo.project.Policy;
import com.cts.demo.service.policyService;

//Marks this class as a REST controller that handles HTTP requests and returns JSON responses
@RestController

//Base URL path for all endpoints in this controller
@RequestMapping("/policy")
public class policyController {

	// Logger for logging request and process information
	private static final Logger logger = LoggerFactory.getLogger(policyController.class);

	// Injects the policyService to handle business logic
	@Autowired
	policyService service;

	// Endpoint to save a new policy
	@PostMapping("/save")
	public String savePolicy(@RequestBody Policy policy) {
		logger.info("Saving policy: {}", policy);
		return service.savePolicy(policy);
	}

	// Endpoint to update an existing policy
	@PutMapping("/update")
	public Policy updatePolicy(@RequestBody Policy policy) {
		logger.info("Updating policy: {}", policy);
		return service.updatePolicy(policy);
	}

	// Endpoint to retrieve a policy by its ID
	@GetMapping("/retrieveById/{pid}")
	public Policy retrievePolicy(@PathVariable("pid") long policyId) throws PolicyNotFoundException {
		logger.info("Retrieving policy by ID: {}", policyId);
		return service.retrievePolicy(policyId);
	}

	// Endpoint to delete (archive) a policy by its ID
	@DeleteMapping("/deletePolicy/{pid}")
	public String archivePolicy(@PathVariable("pid") long policyId) {
		logger.info("Archiving policy with ID: {}", policyId);
		return service.archivePolicy(policyId);
	}

	// Endpoint to retrieve all policies
	@GetMapping("/retrieveAll")
	public List<Policy> retrieveAll() {
		logger.info("Retrieving all policies");
		return service.retrieveAll();
	}

	// Endpoint to assign a policy to a customer
	@PutMapping("/assignPoliciesToCustomer/{pid}/{cid}/{pType}")
	public Customer assignPoliciesToCustomer(@PathVariable("pid") long policyId, @PathVariable("cid") long customerId,
			@PathVariable("pType") String policyType) {
		logger.info("Assigning policy (ID: {}) of type '{}' to customer (ID: {})", policyId, policyType, customerId);
		return service.assignPolicyToCustomer(policyId, customerId, policyType);
	}

	// Endpoint to assign a policy to an agent
	@PutMapping("/assignPoliciesToAgent/{pid}/{aid}/{pType}")
	public Agent assignPoliciesToAgent(@PathVariable("pid") long policyId, @PathVariable("aid") long agentId,
			@PathVariable("pType") String policyType) {
		logger.info("Assigning policy (ID: {}) of type '{}' to agent (ID: {})", policyId, policyType, agentId);
		return service.assignPolicyToAgent(policyId, agentId, policyType);
	}
}
