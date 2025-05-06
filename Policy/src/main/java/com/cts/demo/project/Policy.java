package com.cts.demo.project;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private long policyId;
	private String policyName;
	private double premiumAmount;
	private String coverageDetails;
	private LocalDate validityPeriod;
	private long customerId;
	private long agentId;
}
