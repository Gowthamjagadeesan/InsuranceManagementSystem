package com.cts.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.exception.CustomerNotFoundException;
import com.cts.demo.model.Customer;
import com.cts.demo.model.Policy1;
import com.cts.demo.repository.CustomerRepository;

//Marks this class as a Spring service component with the name "customerService"
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	// Logger for logging information and debugging
	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	// Injects the CustomerRepository to interact with the database
	@Autowired
	CustomerRepository repository;

	// Saves a new customer to the database
	@Override
	public String saveCustomer(Customer customer) {
		logger.info("Saving customer: {}", customer);
		Customer cust = repository.save(customer);
		if (cust != null) {
			logger.info("Customer saved successfully: {}", cust);
			return "Customer saved successfully";
		} else {
			logger.error("Failed to save customer: {}", customer);
			return "Something went wrong";
		}
	}

	// Updates an existing customer's details
	@Override
	public Customer updateCustomer(Customer customer) {
		logger.info("Updating customer: {}", customer);
		return repository.save(customer);
	}

	// Deletes a customer by their ID
	@Override
	public String deleteCustomer(long customerId) {
		logger.info("Deleting customer with ID: {}", customerId);
		repository.deleteById(customerId);
		return "Customer deleted successfully";
	}

	// Searches for a customer by their ID
	@Override
	public Customer searchCustomerById(long customerId) throws CustomerNotFoundException {
		logger.info("Searching for customer by ID: {}", customerId);
		Optional<Customer> optional = repository.findById(customerId);
		if (optional.isPresent()) {
			logger.info("Customer found: {}", optional.get());
			return optional.get();
		} else {
			logger.warn("Customer not found with ID: {}", customerId);
			throw new CustomerNotFoundException("There is no customer in the given Id....");
		}
	}

	// Searches for a customer by their name
	@Override
	public Customer searchCustomerByName(String customerName) {
		logger.info("Searching for customer by name: {}", customerName);
		return repository.findByCustomerName(customerName);
	}

	// Retrieves all customers from the database
	@Override
	public List<Customer> getAllCustomer() {
		logger.info("Fetching all customers");
		return repository.findAll();
	}

	// Assigns a policy to a customer by policy ID, customer ID, and policy type
	@Override
	public Customer assignPoliciesToCustomer(long policyId, long customerId, String policyType)
			throws CustomerNotFoundException {
		logger.info("Assigning policy (ID: {}) of type '{}' to customer (ID: {})", policyId, policyType, customerId);
		Optional<Customer> optional = repository.findById(customerId);
		if (!optional.isPresent()) {
			logger.warn("Customer not found with ID: {}", customerId);
			throw new CustomerNotFoundException("Try a valid customer Id...");
		}

		Customer customer = optional.get();

		// Create a new policy and add it to the customer's list
		Policy1 policy = new Policy1();
		policy.setPolicyId(policyId);
		policy.setPolicyType(policyType);

		List<Policy1> list = new ArrayList<>(customer.getPolicies());
		list.add(policy);
		customer.setPolicies(list);

		// Save the updated customer
		repository.save(customer);
		logger.info("Policy assigned successfully to customer: {}", customer);
		return customer;
	}
}
