package com.cts.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.exception.AgentNotFoundException;
import com.cts.demo.model.Agent;
import com.cts.demo.model.Policy;
import com.cts.demo.repository.AgentRepository;

/**
 * Service implementation for managing Agent-related operations.
 */
@Service("agentService")
public class AgentServiceImpl implements AgentService {

	@Autowired
	AgentRepository repository;

	Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

	/**
	 * Saves a new agent to the database.
	 *
	 * @param agent the agent to be saved
	 * @return confirmation message
	 */
	@Override
	public String saveAgent(Agent agent) {
		repository.save(agent);
		log.info("Inside service Implementation of save agent module");
		return "Agent Saved Successfully";
	}

	/**
	 * Updates an existing agent in the database.
	 *
	 * @param agent the agent with updated details
	 * @return the updated agent
	 */
	@Override
	public Agent updateAgent(Agent agent) {
		log.info("Inside service Implementation of update agent module");
		return repository.save(agent);
	}

	/**
	 * Deletes an agent by ID.
	 *
	 * @param agentId the ID of the agent to delete
	 * @return confirmation message
	 */
	@Override
	public String deleteAgent(long agentId) {
		log.info("Inside service Implementation of delete agent module");
		repository.deleteById(agentId);
		log.info("Agent was deleted successfully");
		return "Agent deleted successfully";
	}

	/**
	 * Searches for an agent by ID.
	 *
	 * @param agentId the ID of the agent to find
	 * @return the found agent
	 * @throws AgentNotFoundException if no agent is found
	 */
	@Override
	public Agent searchAgentById(long agentId) throws AgentNotFoundException {
		log.info("Inside service Implementation of search agent by Id agent module");
		Agent agent = repository.findById(agentId).orElse(null);
		if (agent != null) {
			return agent;
		} else {
			throw new AgentNotFoundException("Enter correct AgentId");
		}
	}

	/**
	 * Searches for an agent by name.
	 *
	 * @param agentName the name of the agent to find
	 * @return the found agent
	 * @throws AgentNotFoundException if no agent is found
	 */
	@Override
	public Agent searchAgentByName(String agentName) throws AgentNotFoundException {
		log.info("Inside service Implementation of search agent by name agent module");
		Agent agent = repository.findByName(agentName);
		log.info("Find by agent name is executed");
		if (agent != null) {
			return agent;
		} else {
			throw new AgentNotFoundException("Enter a valid AgentName");
		}
	}

	/**
	 * Retrieves all agents from the database.
	 *
	 * @return list of all agents
	 */
	@Override
	public List<Agent> getAllAgent() {
		log.info("Inside service Implementation of search all agent module");
		return repository.findAll();
	}

	/**
	 * Assigns a policy to an agent.
	 *
	 * @param policyId   the ID of the policy
	 * @param agentId    the ID of the agent
	 * @param policyType the type of the policy
	 * @return the updated agent with the new policy
	 * @throws AgentNotFoundException if the agent is not found
	 */
	@Override
	public Agent assignPoliciesToAgent(long policyId, long agentId, String policyType) throws AgentNotFoundException {
		log.info("Inside service Implementation of assign policies to agent module");
		Agent agent = repository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new AgentNotFoundException("Try a valid agent Id...");
		} else {
			Policy policy = new Policy();
			List<Policy> list = new ArrayList<>(agent.getPolicies());
			policy.setPolicyId(policyId);
			policy.setAssignedPolicies(policyType);
			list.add(policy);
			agent.setPolicies(list);
			repository.save(agent);
			log.info("Policy is assigned to the agent successfully");
			return agent;
		}
	}

	@Override
	public Agent findAgentByPolicyId(long policyId) {
		
		return repository.findAgentByPolicyId(policyId);
	}

	@Override
	public List<Policy> getPolicyByAgent(long agentId) {

		Optional<Agent> opt= repository.findById(agentId);
		return opt.get().getPolicies();
	}

	@Override
	public Agent removePolicyFromCustomer(Long policyId) throws AgentNotFoundException {
		Agent agent = repository.findAgentByPolicyId(policyId);
		if (agent == null) {
			throw new AgentNotFoundException("There is no customer in the given Id....");
		}

		List<Policy> updatedPolicies = agent.getPolicies().stream()
				.filter(policy -> policy.getPolicyId() != policyId).collect(Collectors.toList());

		agent.setPolicies(updatedPolicies);
		repository.save(agent);

		repository.deleteById(policyId);
		return agent;
	}
}
