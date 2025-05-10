package com.cts.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Policy {

	// Unique identifier for the policy
	private long policyId;

	// Name or type of the policy (e.g., Health, Life, Vehicle)
	private String policyName;

	// Premium amount associated with the policy
	private double premiumAmount;

	// Description of what the policy covers
	private String coverageDetails;

	// The date until which the policy is valid
	private LocalDate validityPeriod;
}
