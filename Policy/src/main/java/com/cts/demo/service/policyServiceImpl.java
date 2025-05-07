package com.cts.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.Agent;
import com.cts.demo.dto.Customer;
import com.cts.demo.feignclient.AgentClient;
import com.cts.demo.feignclient.CustomerClient;
import com.cts.demo.feignclient.NotificationClient;
import com.cts.demo.project.Policy;
import com.cts.demo.repository.policyRepository;

@Service("policyService")
public class policyServiceImpl implements policyService {

	@Autowired
	policyRepository repository;

	@Autowired
	CustomerClient customerClient;

	@Autowired
	AgentClient agentClient;

	@Autowired
	NotificationClient notificationClient;

	@Override
	public String savePolicy(Policy policy) {
		Policy policy1 = repository.save(policy);
		notificationClient.notify("A New Policy is been created", policy1.getPolicyId(),
				policy1.getPolicyId());
		if (policy1 != null) {
			return "Policy saved Successfully";
		} else {
			return "Policy not saved ....";
		}
	}

	@Override
	public Policy updatePolicy(Policy policy) {
		return repository.save(policy);
	}

	@Override
	public Policy retrievePolicy(long policyId) {
		Optional<Policy> optional = repository.findById(policyId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	@Override
	public String archivePolicy(long policyId) {
		repository.deleteById(policyId);
		return "Policy deleted Successfully";
	}

	@Override
	public List<Policy> retrieveAll() {
		return repository.findAll();
	}

	@Override
	public Customer assignPolicyToCustomer(long policyId, long customerId, String policyType) {
		notificationClient.notify(policyType + " is assigne to you", customerId, policyId);

		return customerClient.assignPoliciesToCustomer(policyId, customerId, policyType);
	}

	@Override
	public Agent assignPolicyToAgent(long policyId, long agentId, String policyType) {

		return agentClient.assignPoliciesToAgent(policyId, agentId, policyType);
	}

}
