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
import com.cts.demo.service.AgentService;

//Marks this class as a REST controller to handle HTTP requests
@RestController
//Base URL path for all endpoints in this controller
@RequestMapping("/agent")
public class AgentController {

//Logger instance for logging controller activities
	Logger log = LoggerFactory.getLogger(AgentController.class);

//Injecting the AgentService to delegate business logic
	@Autowired
	AgentService service;

//Endpoint to save a new agent
	@PostMapping("/save")
	public String saveAgent(@RequestBody @Validated Agent Agent) {
		log.info("activating save agent in controller");
		return service.saveAgent(Agent);
	}

//Endpoint to update an existing agent
	@PutMapping("/update")
	public Agent updateAgent(@RequestBody @Validated Agent Agent) {
		log.info("activating update agent in controller");
		return service.updateAgent(Agent);
	}

//Endpoint to delete an agent by ID
	@DeleteMapping("/delete/{aid}")
	public String deleteAgent(@PathVariable("aid") @Validated long AgentId) {
		log.info("activating delete agent in controller");
		return service.deleteAgent(AgentId);
	}

//Endpoint to search for an agent by ID
	@GetMapping("/searchById/{aid}")
	public Agent searchAgentById(@PathVariable("aid") @Validated long AgentId) throws AgentNotFoundException {
		log.info("activating the module search agent by Id in controller");
		return service.searchAgentById(AgentId);
	}

//Endpoint to search for an agent by name
	@GetMapping("/searchByName/{aname}")
	public Agent searchAgentByName(@PathVariable("aname") @Validated String AgentName) throws AgentNotFoundException {
		log.info("activating the module search agent by name in controller");
		return service.searchAgentByName(AgentName);
	}

//Endpoint to retrieve all agents
	@GetMapping("/searchAll")
	public List<Agent> searchAll() {
		log.info("activating search all agent in controller");
		return service.getAllAgent();
	}

//Endpoint to assign a policy to an agent
	@PutMapping("/assignPoliciesToAgent/{pid}/{aid}/{ptype}")
	public Agent assignPoliciesToAgent(@PathVariable("pid") long policyId, @PathVariable("aid") long agentId,
			@PathVariable("ptype") String policyType) throws AgentNotFoundException {
		log.info("activating assign policies to agent in controller");
		return service.assignPoliciesToAgent(policyId, agentId, policyType);
	}
}
