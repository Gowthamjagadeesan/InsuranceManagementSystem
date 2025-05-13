package com.cts.demo.service;

import java.util.List;

import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.model.Claim;

/**
 * Service interface for handling business logic related to insurance claims.
 */
public interface ClaimService {

	/**
	 * Files a new insurance claim.
	 *
	 * @param claim the claim to be filed
	 * @return confirmation message
	 */
	String fileClaim(Claim claim);

	/**
	 * Reviews a claim based on its ID and the claim amount.
	 *
	 * @param claimId the ID of the claim
	 * @return the reviewed claim
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	Claim reviewClaimByIdAndAmount(long claimId) throws ClaimNotFoundException;

	/**
	 * Retrieves the status of a claim by its ID.
	 *
	 * @param claimId the ID of the claim
	 * @return the status of the claim
	 */
	String claimStatus(long claimId);

	/**
	 * Reviews a claim based on its ID and the validity period of the associated
	 * policy.
	 *
	 * @param claimId the ID of the claim
	 * @return the reviewed claim
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	Claim reviewClaimByIdAndValidityPeriod(long claimId) throws ClaimNotFoundException;

	/**
	 * Retrieves a claim by its ID.
	 *
	 * @param claimId the ID of the claim
	 * @return the claim object
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	Claim getClaimById(long claimId) throws ClaimNotFoundException;

	/**
	 * Retrieves all claims from the database.
	 *
	 * @return list of all claims
	 */
	List<Claim> getAllClaims();

	/**
	 * Deletes a claim by its ID.
	 *
	 * @param claimId the ID of the claim to delete
	 * @return confirmation message
	 */
	String deleteClaimById(long claimId);
}
