package com.cts.service;

import com.cts.exception.CustomerNotFoundException;
import com.cts.model.Customer;

public interface CustomerService {
	public abstract String saveCustomer(Customer customer);

	public abstract Customer updateCustomer(Customer customer);

	public abstract String deleteCustomer(long customerId);

	public abstract Customer searchCustomerById(long customerId) throws CustomerNotFoundException;

	public abstract Customer searchCustomerByName(String customerName);
}
