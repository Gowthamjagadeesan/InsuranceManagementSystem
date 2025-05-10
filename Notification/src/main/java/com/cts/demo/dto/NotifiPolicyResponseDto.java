package com.cts.demo.dto;

import com.cts.demo.entity.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class NotifiPolicyResponseDto {

	// Represents the policy details associated with the notification
	private Policy policy;

	// Represents the notification details
	private Notification notification;
}
