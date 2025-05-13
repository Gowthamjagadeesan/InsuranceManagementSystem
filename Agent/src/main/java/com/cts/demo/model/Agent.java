package com.cts.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an Agent entity mapped to a database table. Includes validation
 * and relationship mappings.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

	/**
	 * Primary key for the Agent entity. Must be greater than 10.
	 */
	@Id
	@Min(value = 10, message = "The value should be greater than 10")
	private long agentId;

	/**
	 * Name of the agent. Cannot be null or empty.
	 */
	@NotEmpty(message = "Agent Name should not be null or blank")
	private String agentName;

	/**
	 * Contact information for the agent. Cannot be null or empty.
	 */
	@NotEmpty(message = "Agent contact_info Should not be null or blank")
	private String contactInfo;

	/**
	 * List of policies associated with the agent. Uses a one-to-many relationship
	 * with cascading and eager fetching.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "agent_id", referencedColumnName = "agentId")
	private List<Policy> policies;

	/**
	 * Custom constructor excluding the policies list. Useful for creating agent
	 * instances without associated policies.
	 */
	public Agent(@NotEmpty(message = "Agent Id Should not be empty") long agentId,
			@NotEmpty(message = "Agent Name should not be null or blank") String agentName,
			@NotEmpty(message = "Agent contact_info Should not be null or blank") String contactInfo) {
		this.agentId = agentId;
		this.agentName = agentName;
		this.contactInfo = contactInfo;
	}
}
