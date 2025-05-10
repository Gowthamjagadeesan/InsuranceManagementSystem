package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.demo.model.Agent;

//This interface extends JpaRepository to provide CRUD operations for the Agent entity
public interface AgentRepository extends JpaRepository<Agent, Long> {

//Custom query method to find an agent by their name
	public abstract Agent findByAgentName(String agentName);
}
