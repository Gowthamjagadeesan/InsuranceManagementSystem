package com.cts.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cts.demo.dto.Agent;

//Declares this interface as a Feign client for communicating with the "AGENT" microservice
//All requests made through this client will be prefixed with "/agent"
@FeignClient(name = "AGENT", path = "/agent")
public interface AgentClient {

	// Maps to GET /agent/searchById/{aid}
	// Retrieves an Agent by their ID
	@GetMapping("/searchById/{aid}")
	public Agent getAgent(@PathVariable("aid") long agentId);

	// Maps to PUT /agent/assignPoliciesToAgent/{pid}/{aid}/{pType}
	// Assigns a policy to an agent by policy ID, agent ID, and policy type
	@PutMapping("/assignPoliciesToAgent/{pid}/{aid}/{pType}")
	public Agent assignPoliciesToAgent(@PathVariable("pid") long policyId, @PathVariable("aid") long customerId,
			@PathVariable("pType") String policyType);
}
