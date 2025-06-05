package com.cts.demo.project;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Marks this class as a JPA entity, meaning it maps to a database table
@Entity

//Specifies the name of the table in the database
@Table(name = "policy_info")

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Policy {

	// Primary key for the policy entity
	@Id

	// Ensures the policy ID is a positive number
	@Positive(message = "The value should not be negative")
	private long policyId;

	// Name of the policy; must not be empty
	@NotEmpty(message = "Customer Name should not be null or blank")
	private String policyName;

	// Premium amount must be at least 50,000
	@Min(value = 10000, message = "Enter a valid premium amount")
	@Max(value = 1000000, message = "Maximum amount reached")
	private double premiumAmount;

	// Description of what the policy covers; must not be empty
	@NotEmpty(message = "Coverage details Should not be empty")
	private String coverageDetails;

	// The date until which the policy is valid
	private LocalDate validityPeriod;
}
