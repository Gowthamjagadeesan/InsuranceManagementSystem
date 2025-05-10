package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.demo.model.Customer;

//This interface extends JpaRepository to provide CRUD operations for the Customer entity
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// Custom query method to find a customer by their name
	// Spring Data JPA will automatically implement this based on the method name
	public abstract Customer findByCustomerName(String customerName);
}
