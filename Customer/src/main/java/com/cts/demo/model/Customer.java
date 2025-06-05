package com.cts.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Marks this class as a JPA entity, meaning it maps to a database table
@Entity

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Specifies the name of the database table this entity maps to
@Table(name = "customer")
public class Customer {

	// Marks this field as the primary key
	@Id

	// Ensures the customer ID is a positive number

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;

	// Ensures the customer name is not null or blank
	@NotEmpty(message = "Customer Name should not be null or blank")
	private String name;

	// Validates that the email is in a proper format
	@Email(message = "Enter a valid email Id")
	private String email;

	// Validates that the phone number is between 10 and 12 characters
	@Size(max = 12, min = 10, message = "Enter a valid phone number")
	private String customerPhone;

	// Ensures the address is not empty
	@NotEmpty(message = "Address should not be empty")
	private String customerAddress;

	// One-to-many relationship with Policy1 entity
	// CascadeType.ALL means all operations (persist, merge, remove, etc.) will
	// cascade to policies
	// FetchType.EAGER means policies will be loaded immediately with the customer
	// @JoinColumn defines the foreign key column in the Policy1 table that refers
	// to customerId
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private List<Policy1> policies;

	// Custom constructor excluding customerId (useful when ID is auto-generated or
	// set elsewhere)
	public Customer(String customerName, String customerEmail, String customerPhone, String customerAddress,
			List<Policy1> policies) {
		super();
		this.name = customerName;
		this.email = customerEmail;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
		this.policies = policies;
	}
}
