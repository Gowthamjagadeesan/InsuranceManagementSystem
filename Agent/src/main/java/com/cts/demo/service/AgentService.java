package com.cts.demo.service;

import java.util.List;

import com.cts.demo.exception.AgentNotFoundException;
import com.cts.demo.model.Agent;

//Service interface for Agent-related operations
public interface AgentService {

//Saves a new agent and returns a confirmation message
	public abstract String saveAgent(Agent agent);

//Updates an existing agent and returns the updated agent object
	public abstract Agent updateAgent(Agent agent);

//Deletes an agent by ID and returns a confirmation message
	public abstract String deleteAgent(long agentId);

//Searches for an agent by ID and throws an exception if not found
	public abstract Agent searchAgentById(long agentId) throws AgentNotFoundException;

//Searches for an agent by name and throws an exception if not found
	public abstract Agent searchAgentByName(String agentName) throws AgentNotFoundException;

//Retrieves a list of all agents
	public abstract List<Agent> getAllAgent();

//Assigns a policy to an agent and returns the updated agent object
	public abstract Agent assignPoliciesToAgent(long policyId, long agentId, String policyType)
			throws AgentNotFoundException;
}
