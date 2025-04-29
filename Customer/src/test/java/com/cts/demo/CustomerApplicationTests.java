package com.cts.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.demo.model.Customer;
import com.cts.demo.repository.CustomerRepository;
import com.cts.demo.service.CustomerServiceImpl;

@SpringBootTest
class CustomerApplicationTests {
	
	@Mock
	CustomerRepository repository;
	
	@InjectMocks
	CustomerServiceImpl service;

	@Test
	void saveTest() {
		Customer customer = new Customer(2113033, "Gowtham", "gowthamjagadeesan322@gmail.com", "6382669477", "salem");
		Mockito.when(repository.save(customer));
		String response = service.saveCustomer(customer);
		assertEquals("Customer saved succussfully", response);
	}

}
