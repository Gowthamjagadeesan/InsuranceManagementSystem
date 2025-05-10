package com.cts.demo.dto;

import com.cts.demo.model.Claim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data

//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor

//Lombok annotation to generate a no-argument constructor
@NoArgsConstructor
public class ClaimPolicyResponseDto {

//Field to hold claim details
	private Claim claim;

//Field to hold policy details associated with the claim
	private Policy policy;
}
