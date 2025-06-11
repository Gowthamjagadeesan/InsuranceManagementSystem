package com.cts.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.demo.exception.CustomerNotFoundException;
import com.cts.demo.model.Customer;
import com.cts.demo.model.Policy1;
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
		Policy1 p1 = new Policy1();
		p1.setPolicyType("Health");
		p1.setPolicyId(201);
		List<Policy1> policies = new ArrayList<>();
		policies.add(p1);
		Customer customer = new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
				policies);
		Mockito.when(repository.save(customer)).thenReturn(customer);
		String response = service.saveCustomer(customer);
		assertEquals("Customer saved successfully", response);
	}

	@Test
	void updateTest() {
		Policy1 p1 = new Policy1();
		p1.setPolicyType("Health");
		p1.setPolicyId(201);
		List<Policy1> policies = new ArrayList<>();
		policies.add(p1);
		Customer customer = new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
				policies);
		Mockito.when(repository.save(customer)).thenReturn(customer);
		Customer cust = service.updateCustomer(customer);
		assertEquals(customer, cust);
	}

	@Test
	void deleteTest() {
		Policy1 p1 = new Policy1();
		p1.setPolicyType("Health");
		p1.setPolicyId(201);
		List<Policy1> policies = new ArrayList<>();
		policies.add(p1);
		Customer customer = new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
				policies);
		long userId = customer.getCustomerId();
		Mockito.doNothing().when(repository).deleteById(userId);
		String response = service.deleteCustomer(userId);
		assertEquals("Customer deleted successfully", response);

	}

	@Test
	void searchCustomerByIdTest() throws CustomerNotFoundException {
		Policy1 p1 = new Policy1();
		p1.setPolicyType("Health");
		p1.setPolicyId(201);
		List<Policy1> policies = new ArrayList<>();
		policies.add(p1);
		Customer customer = new Customer("Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
				policies);
		long customerId = 21113016;
		customer.setCustomerId(customerId);
		Mockito.when(repository.findById(customerId)).thenReturn(Optional.of(customer));
		Customer foundCustomerById = service.searchCustomerById(customerId);
		assertEquals(customer, foundCustomerById);
	}

//	@Test
//	void searchCustomerByNameTest() {
//		String customerName = "Bharath";
//		Policy1 p1 = new Policy1();
//		p1.setPolicyType("Health");
//		p1.setPolicyId(201);
//		List<Policy1> policies = new ArrayList<>();
//		policies.add(p1);
//		Customer customer = new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
//				policies);
//		Mockito.when(repository.findByCustomerName(customerName)).thenReturn(customer);
//		Customer foundCustomerByName = service.searchCustomerByName(customerName);
//		assertEquals(customer, foundCustomerByName);
//	}

	@Test
	void getAllCustomerTest() {
		Policy1 p1 = new Policy1();
		p1.setPolicyType("Health");
		p1.setPolicyId(201);
		List<Policy1> policies = new ArrayList<>();
		policies.add(p1);

		Policy1 p2 = new Policy1();
		p2.setPolicyType("Gold");
		p2.setPolicyId(202);
		List<Policy1> policies2 = new ArrayList<>();
		policies2.add(p2);
		List<Customer> customers = Arrays.asList(
				new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem", policies),
				new Customer(2113016, "Bharathi", "bharathiannadurai@gmail.com", "9443126900", "erode", policies2));
		Mockito.when(repository.findAll()).thenReturn(customers);
		List<Customer> custs = service.getAllCustomer();
		assertEquals(customers, custs);
	}

}
