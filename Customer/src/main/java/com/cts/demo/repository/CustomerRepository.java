package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cts.demo.model.Customer;

import jakarta.transaction.Transactional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public abstract Customer findByCustomerName(String customerName);

	@Modifying
	@Query("UPDATE Customer c SET c.policyId = ?1 WHERE c.customerId = ?2")
	public abstract int assignPoliciesToCustomer(long policyId, long customerId);

}
