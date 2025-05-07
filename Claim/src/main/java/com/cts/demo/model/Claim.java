package com.cts.demo.model;

import jakarta.persistence.Column;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "claim_info")
public class Claim {
	@Id
	@GeneratedValue
	private long claimId;
	private long customerId;
	private long agentId;
	private long policyId;
	@Min(value = 20000, message = "The minimum amount should be 20000rs")
	@Max(value = 500000, message = "The maximim amount should be 500000rs")
	private double claimAmount;
	@NotEmpty(message = "The status should not be empty : IN-REVIEW, ACCEPTED, REJECTED")
	private String claimStatus;

	public Claim(long customerId, long policyId, double claimAmount, String claimStatus) {
		super();
		this.customerId = customerId;
		this.policyId = policyId;
		this.claimAmount = claimAmount;
		this.claimStatus = claimStatus;
	}

}
