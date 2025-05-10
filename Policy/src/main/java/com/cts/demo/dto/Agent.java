package com.cts.demo.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Agent {

	// Unique identifier for the agent
	private long agentId;

	// Name of the agent
	private String agentName;

	// Contact information (e.g., phone number or email)
	private String contactInfo;

	// One-to-many relationship with Policy2 entity
	// CascadeType.ALL: all operations (persist, merge, remove, etc.) will cascade
	// to policies
	// FetchType.EAGER: policies will be loaded immediately with the agent
	// @JoinColumn: defines the foreign key in the Policy2 table that refers to
	// agentId
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "agent_id", referencedColumnName = "agentId")
	private List<Policy2> policies;
}
