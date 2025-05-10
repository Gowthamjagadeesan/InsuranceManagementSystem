package com.cts.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.controller.AgentController;
import com.cts.demo.exception.AgentNotFoundException;
import com.cts.demo.model.Agent;
import com.cts.demo.model.Policy;
import com.cts.demo.repository.AgentRepository;

import lombok.AllArgsConstructor;

//Marks this class as a Spring service component with the name "agentService"
@Service("agentService")
public class AgentServiceImpl implements AgentService {

//Injects the AgentRepository to interact with the database
	@Autowired
	AgentRepository repository;

//Logger instance for logging service-level operations
	Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

//Saves a new agent and returns a confirmation message
	@Override
	public String saveAgent(Agent agent) {
		Agent agent1 = repository.save(agent);
		log.info("Inside service Implementation of save agent module");
		if (agent1 != null) {
			return "Agent Saved Successfully";
		} else {
			return "Problem while saving the project";
		}
	}

//Updates an existing agent and returns the updated agent
	@Override
	public Agent updateAgent(Agent agent) {
		log.info("Inside service Implementation of update agent module");
		return repository.save(agent);
	}

//Deletes an agent by ID and returns a confirmation message
	@Override
	public String deleteAgent(long agentId) {
		log.info("Inside service Implementation of delete agent module");
		repository.deleteById(agentId);
		log.info("Agent " + agentId + " was deleted successfully");
		return "Agent deleted successfully";
	}

//Searches for an agent by ID and throws an exception if not found
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

//Searches for an agent by name and throws an exception if not found
	@Override
	public Agent searchAgentByName(String agentName) throws AgentNotFoundException {
		log.info("Inside service Implementation of search agent by name agent module");
		Agent agent = repository.findByAgentName(agentName);
		log.info("Find by agent name is executed");
		if (agent != null) {
			return agent;
		} else {
			throw new AgentNotFoundException("Enter a valid AgentName");
		}
	}

//Retrieves all agents from the database
	@Override
	public List<Agent> getAllAgent() {
		log.info("Inside service Implementation of search all agent module");
		return repository.findAll();
	}

//Assigns a policy to an agent and returns the updated agent
	@Override
	public Agent assignPoliciesToAgent(long policyId, long agentId, String policyType) throws AgentNotFoundException {
		log.info("Inside service Implementation of assign policies to agent module");
		Agent agent = repository.findById(agentId).orElse(null);
		if (agent == null) {
			throw new AgentNotFoundException("Try a valid agent Id...");
		} else {
			Policy policy = new Policy();
			List<Policy> list = new ArrayList<>(agent.getPolicies()); // Copy existing policies
			policy.setPolicyId(policyId);
			policy.setAssignedPolicies(policyType);
			list.add(policy);
			agent.setPolicies(list);
			repository.save(agent);
			log.info("Policy is assigned to the agent successfully");
			return agent;
		}
	}
}
