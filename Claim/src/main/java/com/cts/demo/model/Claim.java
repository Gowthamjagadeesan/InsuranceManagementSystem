package com.cts.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields as parameters
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor

//Marks this class as a JPA entity, meaning it maps to a database table
@Entity

//Specifies the name of the database table this entity maps to
@Table(name = "claim_info")
public class Claim {

	// Marks this field as the primary key of the entity
	@Id

	// Specifies that the primary key should be automatically generated
	@GeneratedValue
	private long claimId;

	// ID of the customer who filed the claim
	private long customerId;
	
	private String customerMail;

	// ID of the agent handling the claim
	private long agentId;

	// ID of the policy associated with the claim
	private long policyId;

	// Validation constraint to ensure the claim amount is at least 20,000
	@Min(value = 20000, message = "The minimum amount should be 20000rs")

	// Validation constraint to ensure the claim amount does not exceed 500,000
	@Max(value = 500000, message = "The maximum amount should be 500000rs")
	private double claimAmount;

	// Validation constraint to ensure the claim status is not empty
	@NotEmpty(message = "The status should not be empty : IN-REVIEW, ACCEPTED, REJECTED")
	private String claimStatus;

	// Custom constructor excluding claimId and agentId, useful for creating new
	// claims
	public Claim(long customerId, long policyId, double claimAmount, String claimStatus) {
		super();
		this.customerId = customerId;
		this.policyId = policyId;
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
	}
}
