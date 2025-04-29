package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public abstract Customer findByCustomerName(String customerName);

}
