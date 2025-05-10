package com.cts.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Marks this class as a JPA entity, meaning it maps to a database table
@Entity

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class Notification {

	// Primary key for the Notification entity
	@Id

	// Specifies that the ID should be auto-generated using the database's identity
	// column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long notificationId;

	// ID of the customer to whom the notification is related
	private long customerId;

	// ID of the policy associated with the notification
	private long policyId;

	// The message content of the notification
	private String message;
}
