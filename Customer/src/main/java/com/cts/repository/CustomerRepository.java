package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public abstract Customer findByCustomerName(String customerName);

}
