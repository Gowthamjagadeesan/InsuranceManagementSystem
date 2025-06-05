package com.cts.demo.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Customer {

	// Unique identifier for the customer
	private long customerId;

	// Name of the customer
	private String name;

	// Email address of the customer
	private String email;

	// Phone number of the customer
	private String customerPhone;

	// Residential or mailing address of the customer
	private String customerAddress;

	// One-to-many relationship with Policy1 entity
	// CascadeType.ALL: all operations (persist, merge, remove, etc.) will cascade
	// to policies
	// FetchType.EAGER: policies will be loaded immediately with the customer
	// @JoinColumn: defines the foreign key in the Policy1 table that refers to
	// customerId
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private List<Policy1> policies;
}
