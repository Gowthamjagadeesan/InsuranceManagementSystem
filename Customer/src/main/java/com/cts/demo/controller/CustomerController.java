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

/**
 * REST controller for handling customer-related HTTP requests.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService service;

	/**
	 * Creates a new customer.
	 *
	 * @param customer the customer to be created
	 * @return confirmation message
	 */
	@PostMapping("/create")
	public String saveCustomer(@RequestBody Customer customer) {
		logger.info("Creating customer: {}", customer);
		return service.saveCustomer(customer);
	}

	/**
	 * Updates an existing customer.
	 *
	 * @param customer the customer with updated details
	 * @return the updated customer object
	 */
	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		logger.info("Updating customer: {}", customer);
		return service.updateCustomer(customer);
	}

	/**
	 * Deletes a customer by ID.
	 *
	 * @param customerId the ID of the customer to delete
	 * @return confirmation message
	 */
	@DeleteMapping("/delete/{custId}")
	public String deleteCustomer(@PathVariable("custId") long customerId) {
		logger.info("Deleting customer with ID: {}", customerId);
		return service.deleteCustomer(customerId);
	}

	/**
	 * Searches for a customer by ID.
	 *
	 * @param customerId the ID of the customer to find
	 * @return the customer object
	 * @throws CustomerNotFoundException if the customer is not found
	 */
	@GetMapping("/searchById/{custId}")
	public Customer searchCustomerById(@PathVariable("custId") long customerId) throws CustomerNotFoundException {
		logger.info("Searching customer by ID: {}", customerId);
		return service.searchCustomerById(customerId);
	}

	/**
	 * Searches for a customer by name.
	 *
	 * @param customerName the name of the customer to find
	 * @return the customer object
	 */
	@GetMapping("/searchByName/{custName}")
	public Customer searchCustomerByName(@PathVariable("custName") String customerName) {
		logger.info("Searching customer by name: {}", customerName);
		return service.searchCustomerByName(customerName);
	}

	/**
	 * Assigns a policy to a customer.
	 *
	 * @param policyId   the ID of the policy
	 * @param customerId the ID of the customer
	 * @param policyType the type of the policy
	 * @return the updated customer object
	 * @throws CustomerNotFoundException if the customer is not found
	 */
	@PutMapping("/assignPoliciesToCustomer/{pid}/{cid}/{pType}")
	public Customer assignPoliciesToCustomer(@PathVariable("pid") long policyId, @PathVariable("cid") long customerId,
			@PathVariable("pType") String policyType) throws CustomerNotFoundException {
		logger.info("Assigning policy (ID: {}) of type '{}' to customer (ID: {})", policyId, policyType, customerId);
		return service.assignPoliciesToCustomer(policyId, customerId, policyType);
	}
}
