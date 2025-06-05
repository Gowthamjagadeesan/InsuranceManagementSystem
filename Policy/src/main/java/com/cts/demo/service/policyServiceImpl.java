package com.cts.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.Agent;
import com.cts.demo.dto.Customer;
import com.cts.demo.dto.Policy1;
import com.cts.demo.exception.PolicyNotFoundException;
import com.cts.demo.feignclient.AgentClient;
import com.cts.demo.feignclient.CustomerClient;
import com.cts.demo.feignclient.NotificationClient;
import com.cts.demo.project.Policy;
import com.cts.demo.repository.policyRepository;

//Marks this class as a Spring service component with the name "policyService"
@Service("policyService")
public class policyServiceImpl implements policyService {

	// Logger for logging information and debugging
	Logger logger = LoggerFactory.getLogger(policyServiceImpl.class);

	// Injects the repository to interact with the policy database
	@Autowired
	policyRepository repository;

	// Feign client to communicate with the CUSTOMER microservice
	@Autowired
	CustomerClient customerClient;

	// Feign client to communicate with the AGENT microservice
	@Autowired
	AgentClient agentClient;

	// Feign client to communicate with the NOTIFICATION microservice
	@Autowired
	NotificationClient notificationClient;

	// Saves a new policy and sends a notification
	@Override
	public String savePolicy(Policy policy) {
		logger.info("Saving policy: {}", policy);
		Policy policy1 = repository.save(policy);
		logger.info("Policy saved: {}", policy1);
		// Notify about the new policy creation
		List<Customer> customers = customerClient.getAllCustomer();

		for (Customer cust : customers) {
			notificationClient.notify("A New Policy " + policy1.getPolicyName() + " is been created",
					cust.getCustomerId(), policy1.getPolicyId(), cust.getEmail());
		}
		logger.info("Notification sent for new policy creation: {}", policy1.getPolicyName());

		return "Policy saved Successfully";
	}

	// Updates an existing policy
	@Override
	public Policy updatePolicy(Policy policy) {
		logger.info("Updating policy: {}", policy);
		return repository.save(policy);
	}

	// Retrieves a policy by ID
	@Override
	public Policy retrievePolicy(long policyId) throws PolicyNotFoundException {
		logger.info("Retrieving policy with ID: {}", policyId);
		Optional<Policy> optional = repository.findById(policyId);
		if (optional.isPresent()) {
			logger.info("Policy found: {}", optional.get());
			return optional.get();
		} else {
			logger.warn("Policy not found with ID: {}", policyId);
			throw new PolicyNotFoundException("Enter a valid Policy Id");
		}
	}

	// Deletes (archives) a policy by ID
	@Override
	public String archivePolicy(long policyId) {
		logger.info("Archiving policy with ID: {}", policyId);
		repository.deleteById(policyId);
		customerClient.removePolicyFromCustomer(policyId);
		agentClient.removePolicyFromAgent(policyId);
		return "Policy deleted Successfully";
	}

	// Retrieves all policies
	@Override
	public List<Policy> retrieveAll() {
		logger.info("Retrieving all policies");
		return repository.findAll();
	}

	// Assigns a policy to a customer and sends a notification
	@Override
	public Customer assignPolicyToCustomer(long policyId, long customerId, String policyType) {
		logger.info("Assigning policy (ID: {}) of type '{}' to customer (ID: {})", policyId, policyType, customerId);
		Customer customer = customerClient.getCustomer(customerId);
		notificationClient.notify(policyType + " is assigned to you", customerId, policyId,
				customer.getEmail());
		return customerClient.assignPoliciesToCustomer(policyId, customerId, policyType);
	}

	// Assigns a policy to an agent
	@Override
	public Agent assignPolicyToAgent(long policyId, long agentId, String policyType) {
		logger.info("Assigning policy (ID: {}) of type '{}' to agent (ID: {})", policyId, policyType, agentId);
		return agentClient.assignPoliciesToAgent(policyId, agentId, policyType);
	}
}
