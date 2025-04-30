package com.cts.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockitoSession;

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
		Customer customer = new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
				67890);
		Mockito.when(repository.save(customer)).thenReturn(customer);
		String response = service.saveCustomer(customer);
		assertEquals("Customer saved succussfully", response);
	}

	@Test
	void updateTest() {
		Customer customer = new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
				67890);
		Mockito.when(repository.save(customer)).thenReturn(customer);
		Customer cust = service.updateCustomer(customer);
		assertEquals(customer, cust);
	}

	@Test
	void deleteTest() {
		Customer customer = new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
				67890);
		long userId = customer.getCustomerId();
		Mockito.doNothing().when(repository).deleteById(userId);
		String response = service.deleteCustomer(userId);
		assertEquals("Customer deleted successfully", response);

	}

	@Test
	void searchCustomerByIdTest() throws CustomerNotFoundException {
		Customer customer = new Customer("Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem", 67890);
		long customerId = 21113016;
		customer.setCustomerId(customerId);
		Mockito.when(repository.findById(customerId)).thenReturn(Optional.of(customer));
		Customer foundCustomerById = service.searchCustomerById(customerId);
		assertEquals(customer, foundCustomerById);
	}

	@Test
	void searchCustomerByNameTest() {
		String customerName = "Bharath";
		Customer customer = new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem",
				67890);
		Mockito.when(repository.findByCustomerName(customerName)).thenReturn(customer);
		Customer foundCustomerByName = service.searchCustomerByName(customerName);
		assertEquals(customer, foundCustomerByName);
	}

	@Test
	void getAllCustomerTest() {
		List<Customer> customers = Arrays.asList(
				new Customer(2113017, "Bharath", "bharathrahmuthukumaran@gmail.com", "6382669477", "salem", 67890),
				new Customer(2113016, "Bharathi", "bharathiannadurai@gmail.com", "9443126900", "erode", 67891));
		Mockito.when(repository.findAll()).thenReturn(customers);
		List<Customer> custs = service.getAllCustomer();
		assertEquals(customers, custs);
	}

}
