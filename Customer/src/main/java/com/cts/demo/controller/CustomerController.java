package com.cts.demo.controller;

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

import com.cts.demo.exception.CustomerNotFoundException;
import com.cts.demo.model.Customer;
import com.cts.demo.service.CustomerService;

//Marks this class as a REST controller, meaning it handles HTTP requests and returns JSON/XML responses
@RestController

//Base URL path for all endpoints in this controller
@RequestMapping("/customer")
public class CustomerController {

	// Logger for logging request and process information
	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	// Injects the CustomerService to delegate business logic
	@Autowired
	CustomerService service;

	// Endpoint to create a new customer
	@PostMapping("/create")
	public String saveCustomer(@RequestBody Customer customer) {
		logger.info("Creating customer: {}", customer);
		return service.saveCustomer(customer);
	}

	// Endpoint to update an existing customer
	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		logger.info("Updating customer: {}", customer);
		return service.updateCustomer(customer);
	}

	// Endpoint to delete a customer by ID
	@DeleteMapping("/delete/{custId}")
	public String deleteCustomer(@PathVariable("custId") long customerId) {
		logger.info("Deleting customer with ID: {}", customerId);
		return service.deleteCustomer(customerId);
	}

	// Endpoint to search for a customer by ID
	@GetMapping("/searchById/{custId}")
	public Customer searchCustomerById(@PathVariable("custId") long customerId) throws CustomerNotFoundException {
		logger.info("Searching customer by ID: {}", customerId);
		return service.searchCustomerById(customerId);
	}

	// Endpoint to search for a customer by name
	@GetMapping("/searchByName/{custName}")
	public Customer searchCustomerByName(@PathVariable("custName") String customerName) {
		logger.info("Searching customer by name: {}", customerName);
		return service.searchCustomerByName(customerName);
	}

	// Endpoint to assign a policy to a customer
	@PutMapping("/assignPoliciesToCustomer/{pid}/{cid}/{pType}")
	public Customer assignPoliciesToCustomer(@PathVariable("pid") long policyId, @PathVariable("cid") long customerId,
			@PathVariable("pType") String policyType) throws CustomerNotFoundException {
		logger.info("Assigning policy (ID: {}) of type '{}' to customer (ID: {})", policyId, policyType, customerId);
		return service.assignPoliciesToCustomer(policyId, customerId, policyType);
	}
}
