package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.demo.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
	public abstract Agent findByAgentName(String AgentName);
}
