package com.cts.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.model.Claim;
import com.cts.demo.service.ClaimService;

/**
 * REST controller for handling claim-related HTTP requests.
 */
@RestController
@RequestMapping("/claim")
public class ClaimController {

	Logger log = LoggerFactory.getLogger(ClaimController.class);

	@Autowired
	ClaimService service;

	/**
	 * Endpoint to file a new claim.
	 *
	 * @param claim the claim to be filed
	 * @return confirmation message
	 */
	@PostMapping("/file")
	public String save(@RequestBody Claim claim) {
		log.info("Inside controller of the save claim module");
		return service.fileClaim(claim);
	}

	/**
	 * Endpoint to review a claim based on its validity period.
	 *
	 * @param claimId the ID of the claim to review
	 * @return the reviewed claim
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	@PutMapping("/reviewClaimByValidityPeriod/{cid}")
	public Claim reviewClaim(@PathVariable("cid") long claimId) throws ClaimNotFoundException {
		log.info("Inside controller of the review claim by id and Validity period module");
		return service.reviewClaimByIdAndValidityPeriod(claimId);
	}

	/**
	 * Endpoint to review a claim based on the claim amount.
	 *
	 * @param claimId the ID of the claim to review
	 * @return the reviewed claim
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	@PutMapping("/reviewClaimByAmount/{cid}")
	public Claim reviewClaimByAmount(@PathVariable("cid") long claimId) throws ClaimNotFoundException {
		log.info("Inside controller of the review claim by id and amount module");
		return service.reviewClaimByIdAndAmount(claimId);
	}

	/**
	 * Endpoint to get the status of a claim.
	 *
	 * @param claimId the ID of the claim
	 * @return the status of the claim
	 */
	@GetMapping("/claimStatus/{cid}")
	public String claimStatus(@PathVariable("cid") long claimId) {
		log.info("Inside controller of the get claim status module");
		return service.claimStatus(claimId);
	}

	/**
	 * Endpoint to retrieve a claim by its ID.
	 *
	 * @param claimId the ID of the claim
	 * @return the claim object
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	@GetMapping("/retrieveClaimById/{cid}")
	public Claim getClaimById(@PathVariable("cid") long claimId) throws ClaimNotFoundException {
		log.info("Inside controller of the get claim by Id module");
		return service.getClaimById(claimId);
	}

	/**
	 * Endpoint to retrieve all claims.
	 *
	 * @return list of all claims
	 */
	@GetMapping("/retrieveAll")
	public List<Claim> getAllClaims() {
		log.info("Inside controller of the get list of all claims module");
		return service.getAllClaims();
	}

	/**
	 * Endpoint to delete a claim by its ID.
	 *
	 * @param claimId the ID of the claim to delete
	 * @return confirmation message
	 */
	@DeleteMapping("/deleteById/{cid}")
	public String deleteClaimById(@PathVariable("cid") long claimId) {
		log.info("Inside controller of the delete claim by Id module");
		return service.deleteClaimById(claimId);
	}
}
