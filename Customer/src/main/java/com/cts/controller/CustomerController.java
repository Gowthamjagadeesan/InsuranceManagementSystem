package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.exception.CustomerNotFoundException;
import com.cts.model.Customer;
import com.cts.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;

	@PostMapping(value = "/save")
	public String saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}

	@PutMapping(value = "/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}

	@DeleteMapping(value = "/delete/{custId}")
	public String deleteCustomer(@PathVariable("custId") long customerId) {
		return service.deleteCustomer(customerId);
	}

	@GetMapping(value = "/searchById/{custId}")
	public Customer searchCustomerById(@PathVariable("custId") long customerId) throws CustomerNotFoundException {
		return service.searchCustomerById(customerId);
	}

	@GetMapping(value = "/searchByName/{custName}")
	public Customer searchCustomerByName(@PathVariable("custName") String customerName) {
		return service.searchCustomerByName(customerName);
	}

}
