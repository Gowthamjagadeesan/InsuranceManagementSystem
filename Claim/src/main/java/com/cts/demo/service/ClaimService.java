package com.cts.demo.service;

import java.util.List;

import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.model.Claim;

//Service interface for handling business logic related to insurance claims
public interface ClaimService {

	// Method to file a new claim
	// Accepts a Claim object and returns a confirmation message
	public abstract String fileClaim(Claim claim);

	// Method to review a claim based on its ID and the claim amount
	// Throws ClaimNotFoundException if the claim is not found
	public abstract Claim reviewClaimByIdAndAmount(long claimId) throws ClaimNotFoundException;

	// Method to retrieve the status of a claim by its ID
	public abstract String claimStatus(long claimId);

	// Method to review a claim based on its ID and the validity period of the
	// associated policy
	// Throws ClaimNotFoundException if the claim is not found
	public abstract Claim reviewClaimByIdAndValidityPeriod(long claimId) throws ClaimNotFoundException;

	// Method to retrieve a claim by its ID
	// Throws ClaimNotFoundException if the claim is not found
	public abstract Claim getClaimById(long claimId) throws ClaimNotFoundException;

	// Method to retrieve all claims from the database
	public abstract List<Claim> getAllClaims();

	// Method to delete a claim by its ID
	// Returns a confirmation message
	public abstract String deleteClaimById(long claimId);
}
