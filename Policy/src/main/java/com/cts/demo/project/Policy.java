package com.cts.demo.project;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "policy_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
	@Id
	@Positive(message = "The value should not be negative")
	private long policyId;
	@NotEmpty(message = "Customer Name should not be null or blank")
	private String policyName;
	@Min(value = 50000, message = "Enter a valid premium amount")
	private double premiumAmount;
	@NotEmpty(message = "Coverage details Should not be empty")
	private String coverageDetails;
	//@Future(message = "The validityPeriod should be in the future")
	private LocalDate validityPeriod;
	
}
