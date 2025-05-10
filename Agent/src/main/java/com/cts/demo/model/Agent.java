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

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Marks this class as a JPA entity mapped to a database table
@Entity

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Agent {

//Primary key for the Agent entity
	@Id
//Validation to ensure agentId is greater than 10
	@Min(value = 10, message = "The value should be greater than 10")
	private long agentId;

//Validation to ensure agentName is not null or empty
	@NotEmpty(message = "Agent Name should not be null or blank")
	private String agentName;

//Validation to ensure contactInfo is not null or empty
	@NotEmpty(message = "Agent contact_info Should not be null or blank")
	private String contactInfo;

//One-to-many relationship with Policy entity
//CascadeType.ALL ensures all operations (persist, merge, remove, etc.) are cascaded
//FetchType.EAGER loads policies immediately with the agent
//JoinColumn defines the foreign key in the Policy table referencing agentId
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "agent_id", referencedColumnName = "agentId")
	private List<Policy> policies;

//Custom constructor excluding policies list, useful for creating agents without policies
	public Agent(@NotEmpty(message = "Agent Id Should not be empty") long agentId,
			@NotEmpty(message = "Agent Name should not be null or blank") String agentName,
			@NotEmpty(message = "Agent contact_info Should not be null or blank") String contactInfo) {
		super();
		this.agentId = agentId;
		this.agentName = agentName;
		this.contactInfo = contactInfo;
	}
}
