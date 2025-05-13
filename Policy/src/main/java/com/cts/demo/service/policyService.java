package com.cts.demo.service;

import java.util.List;

import com.cts.demo.dto.Agent;
import com.cts.demo.dto.Customer;
import com.cts.demo.project.Policy;

//Service interface for handling business logic related to Policy operations
public interface policyService {

	// Saves a new policy to the database
	public abstract String savePolicy(Policy policy);

	// Updates an existing policy's details
	public abstract Policy updatePolicy(Policy policy);

	// Retrieves a policy by its ID
	public abstract Policy retrievePolicy(long policyId);

	// Archives (or deletes) a policy by its ID
	public abstract String archivePolicy(long policyId);

	// Retrieves all policies from the database
	public abstract List<Policy> retrieveAll();

	// Assigns a policy to a customer by policy ID, customer ID, and policy type
	public abstract Customer assignPolicyToCustomer(long policyId, long customerId, String policyType);

	// Assigns a policy to an agent by policy ID, agent ID, and policy type
	public abstract Agent assignPolicyToAgent(long policyId, long agentId, String policyType);
}
