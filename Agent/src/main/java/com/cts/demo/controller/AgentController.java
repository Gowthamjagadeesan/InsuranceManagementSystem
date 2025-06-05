package com.cts.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.demo.exception.AgentNotFoundException;
import com.cts.demo.model.Agent;
import com.cts.demo.model.Policy;
import com.cts.demo.service.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {

	Logger log = LoggerFactory.getLogger(AgentController.class);

	@Autowired
	AgentService service;

	/**
	 * Handles HTTP POST requests to save a new agent.
	 * 
	 * @param agent the agent object to be saved
	 * @return a confirmation message from the service layer
	 */
	@PostMapping("/save")
	public String saveAgent(@RequestBody @Validated Agent agent) {
		log.info("activating save agent in controller");
		return service.saveAgent(agent);
	}

	/**
	 * Handles HTTP PUT requests to update an existing agent.
	 * 
	 * @param agent the updated agent object
	 * @return the updated agent object from the service layer
	 */
	@PutMapping("/update")
	public Agent updateAgent(@RequestBody @Validated Agent agent) {
		log.info("activating update agent in controller");
		return service.updateAgent(agent);
	}

	/**
	 * Handles HTTP DELETE requests to delete an agent by ID.
	 * 
	 * @param agentId the ID of the agent to be deleted
	 * @return a confirmation message from the service layer
	 */
	@DeleteMapping("/delete/{aid}")
	public String deleteAgent(@PathVariable("aid") @Validated long agentId) {
		log.info("activating delete agent in controller");
		return service.deleteAgent(agentId);
	}

	/**
	 * Handles HTTP GET requests to retrieve an agent by ID.
	 * 
	 * @param agentId the ID of the agent to be retrieved
	 * @return the agent object if found
	 * @throws AgentNotFoundException if the agent is not found
	 */
	@GetMapping("/searchById/{aid}")
	public Agent searchAgentById(@PathVariable("aid") @Validated long agentId) throws AgentNotFoundException {
		log.info("activating the module search agent by Id in controller");
		return service.searchAgentById(agentId);
	}

	/**
	 * Handles HTTP GET requests to retrieve an agent by name.
	 * 
	 * @param agentName the name of the agent to be retrieved
	 * @return the agent object if found
	 * @throws AgentNotFoundException if the agent is not found
	 */
	@GetMapping("/searchByName/{aname}")
	public Agent searchAgentByName(@PathVariable("aname") @Validated String agentName) throws AgentNotFoundException {
		log.info("activating the module search agent by name in controller");
		return service.searchAgentByName(agentName);
	}

	/**
	 * Handles HTTP GET requests to retrieve all agents.
	 * 
	 * @return a list of all agent objects
	 */
	@GetMapping("/searchAll")
	public List<Agent> searchAll() {
		log.info("activating search all agent in controller");
		return service.getAllAgent();
	}

	/**
	 * Handles HTTP PUT requests to assign a policy to an agent.
	 * 
	 * @param policyId   the ID of the policy to assign
	 * @param agentId    the ID of the agent to whom the policy is assigned
	 * @param policyType the type of the policy
	 * @return the updated agent object with the assigned policy
	 * @throws AgentNotFoundException if the agent is not found
	 */
	@PutMapping("/assignPoliciesToAgent/{pid}/{aid}/{ptype}")
	public Agent assignPoliciesToAgent(@PathVariable("pid") long policyId, @PathVariable("aid") long agentId,
			@PathVariable("ptype") String policyType) throws AgentNotFoundException {
		log.info("activating assign policies to agent in controller");
		return service.assignPoliciesToAgent(policyId, agentId, policyType);
	}
	@GetMapping("/getAgentByPolicy/{pid}")
	public Agent findAgentByPolicyId(@PathVariable("pid") long policyId) {
		return service.findAgentByPolicyId(policyId);
	}
	
	@GetMapping("/getPolicyByAgent/{id}")
	public List<Policy> getPolicyByAgent(@PathVariable("id") long agentId) {
		return service.getPolicyByAgent(agentId);
	}
	
	@DeleteMapping("/remove-policy/{pid}")
	public  Agent removePolicyFromAgent(@PathVariable("pid") Long policyId) throws AgentNotFoundException{
		return service.removePolicyFromCustomer(policyId);
	}
	
	
}
