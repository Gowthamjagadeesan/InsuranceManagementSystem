package com.cts.demo.service;

import java.util.List;

import com.cts.demo.exception.CustomerNotFoundException;
import com.cts.demo.model.Customer;
import com.cts.demo.model.Policy1;

//Service interface for handling business logic related to Customer operations
public interface CustomerService {

	// Saves a new customer to the database
	public abstract String saveCustomer(Customer customer);

	// Updates an existing customer's details
	public abstract Customer updateCustomer(Customer customer);

	// Deletes a customer by their ID
	public abstract String deleteCustomer(long customerId);

	// Searches for a customer by their ID
	// Throws CustomerNotFoundException if the customer is not found
	public abstract Customer searchCustomerById(long customerId) throws CustomerNotFoundException;

	// Searches for a customer by their name
	public abstract Customer searchCustomerByName(String customerName);

	// Retrieves a list of all customers
	public abstract List<Customer> getAllCustomer();

	// Assigns a policy to a customer by policy ID, customer ID, and policy type
	// Throws CustomerNotFoundException if the customer is not found
	public abstract Customer assignPoliciesToCustomer(long policyId, long customerId, String policyType)
			throws CustomerNotFoundException;

	public abstract  List<Policy1> getPolicyByCustomer(long customerId);
	
	public abstract  Customer removePolicyFromCustomer(Long policyId) throws CustomerNotFoundException ;
	
	
}
