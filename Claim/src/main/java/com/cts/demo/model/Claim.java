package com.cts.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Column(name = "claim_id")
	private long claimId;
	private long customerId;
	private long policyId;
	private double claimAmount;
	private String claimStatus;
}
