package com.cts.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

	@Id
	private long agentId;
	private String agentName;
	private String contactInfo;
	private String assignedPolicies;
	private long policyId;

}
