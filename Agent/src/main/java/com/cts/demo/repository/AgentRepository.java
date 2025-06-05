package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.demo.model.Agent;

//This interface extends JpaRepository to provide CRUD operations for the Agent entity
//@Transactional
public interface AgentRepository extends JpaRepository<Agent, Long> {

//Custom query method to find an agent by their name
	public abstract Agent findByName(String agentName);
	
//	@Modifying
	@Query("SELECT a FROM Agent a JOIN a.policies p WHERE p.policyId = :policyId")
	public  abstract Agent findAgentByPolicyId(Long policyId);

}
