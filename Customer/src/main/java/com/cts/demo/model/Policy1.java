package com.cts.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Marks this class as a JPA entity, meaning it maps to a database table
@Entity

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Policy1 {

	// Marks this field as the primary key
	@Id
	private long policyId;

	// Ensures the policy type is not empty
	@NotEmpty(message = "Policy name should not be empty")
	private String policyType;
}
