package com.cts.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
	@Id
	@Positive(message = "The value should not be negative")
	private long customerId;
	@NotEmpty(message = "Customer Name should not be null or blank")
	private String customerName;
	@Email(message = "Enter a valid email Id")
	private String customerEmail;
	@Size(max = 12, min = 10, message = "Enter a valid phone number")
	private String customerPhone;
	@NotEmpty(message = "Address should not be empty")
	private String customerAddress;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private List<Policy1> policies;

	public Customer(String customerName, String customerEmail, String customerPhone, String customerAddress,
			List<Policy1> policies) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
		this.policies = policies;
	}

}
