package com.cts.demo.service;

import com.cts.demo.exception.CustomerNotFoundException;
import com.cts.demo.model.Customer;

public interface CustomerService {
	public abstract String saveCustomer(Customer customer);

	public abstract Customer updateCustomer(Customer customer);

	public abstract String deleteCustomer(long customerId);

	public abstract Customer searchCustomerById(long customerId) throws CustomerNotFoundException;

	public abstract Customer searchCustomerByName(String customerName);
}
