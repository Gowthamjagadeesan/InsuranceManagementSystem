package com.cts.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Policy entity mapped to a database table. Includes validation
 * constraints for fields.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

	/**
	 * Primary key for the Policy entity. Must be a positive number.
	 */
	@Id
	@Min(value = 1, message = "The Id should not be negative or zero...")
	private long policyId;

	/**
	 * Description or name of the assigned policy. Cannot be empty.
	 */
	@NotEmpty(message = "policies should not be empty...")
	private String assignedPolicies;
}
