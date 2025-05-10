package com.cts.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Marks this class as a JPA entity mapped to a database table
@Entity

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Policy {

//Primary key for the Policy entity
	@Id
//Validation to ensure policyId is greater than 0
	@Min(value = 1, message = "The Id should not be negative or zero...")
	private long policyId;

//Validation to ensure assignedPolicies is not empty
	@NotEmpty(message = "policies should not be empty...")
	private String assignedPolicies;
}
