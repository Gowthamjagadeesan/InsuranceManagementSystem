package com.cts.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.dto.Policy;
import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.feignclient.NotificationClient;
import com.cts.demo.feignclient.PolicyClient;
import com.cts.demo.model.Claim;
import com.cts.demo.repository.ClaimRepository;

/**
 * Service implementation for handling business logic related to insurance
 * claims.
 */
@Service("claimService")
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimRepository repository;

	@Autowired
	PolicyClient policyClient;

	@Autowired
	NotificationClient notificationClient;

	Logger log = LoggerFactory.getLogger(ClaimServiceImpl.class);

	/**
	 * Files a new claim and saves it to the database.
	 *
	 * @param claim the claim to be filed
	 * @return confirmation message
	 */
	@Override
	public String fileClaim(Claim claim) {
		log.info("Inside service implementation of the file claim module");
		Claim c = repository.save(claim);
		return (c != null) ? "New claim request has been filed successfully" : "Something went wrong";
	}

	/**
	 * Reviews a claim based on its amount and updates its status. Sends
	 * notifications based on the outcome.
	 *
	 * @param claimId the ID of the claim
	 * @return the updated claim
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	@Override
	public Claim reviewClaimByIdAndAmount(long claimId) throws ClaimNotFoundException {
		log.info("Inside service implementation of review claim by amount module");
		Optional<Claim> optional = repository.findById(claimId);

		if (!optional.isPresent()) {
			throw new ClaimNotFoundException("There is no claim in the given Claim ID...");
		}

		Claim claim = optional.get();
		Policy policy = policyClient.retrievePolicy(claim.getPolicyId());
		double maxAmt = policy.getPremiumAmount();
		double claimAmt = claim.getClaimAmount();

		if (claimAmt <= maxAmt) {
			repository.updateClaimStatus("APPROVED", claimId);
			log.info("The claim status changed to approved");

			notificationClient.sendNotification("Your Policy validity period is less than 10 days", claimId,
					policy.getPolicyId());
			notificationClient.notify("Claim status Changed to APPROVED!!!!", claim.getCustomerId(),
					policy.getPolicyId());
		} else {
			repository.updateClaimStatus("REJECTED", claimId);
			log.warn("The claim status changed to Rejected");

			notificationClient.notify("Claim status Changed to REJECTED!!!! Kindly verify", claim.getCustomerId(),
					policy.getPolicyId());
		}
		Optional<Claim> optionalClaim = repository.findById(claimId);
		if (optionalClaim.isPresent()) {
			return optionalClaim.get();
		} else {
			throw new ClaimNotFoundException("The claim is not present");
		}
	}

	/**
	 * Retrieves the status of a claim and sends a notification.
	 *
	 * @param claimId the ID of the claim
	 * @return the current status of the claim
	 */
	@Override
	public String claimStatus(long claimId) {
		log.info("Inside service implementation of getting claim status module");
		Optional<Claim> optional = repository.findById(claimId);

		String status = optional.get().getClaimStatus();
		notificationClient.notify("Your claim status is " + status, optional.get().getCustomerId(),
				optional.get().getClaimId());
		log.info("The Notifications are stored in Table");

		return status;
	}

	/**
	 * Reviews a claim based on the validity period of the associated policy.
	 * Updates the claim status and sends notifications accordingly.
	 *
	 * @param claimId the ID of the claim
	 * @return the updated claim
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	@Override
	public Claim reviewClaimByIdAndValidityPeriod(long claimId) throws ClaimNotFoundException {
		log.info("Inside service implementation of review claim by Validity period module");
		Optional<Claim> optional = repository.findById(claimId);

		if (!optional.isPresent()) {
			throw new ClaimNotFoundException("There is no claim in the given Claim ID...");
		}

		Claim claim = optional.get();
		Policy policy = policyClient.retrievePolicy(claim.getPolicyId());
		LocalDate policyValidity = policy.getValidityPeriod();
		LocalDate now = LocalDate.now();

		if (!policyValidity.isBefore(now)) {
			repository.updateClaimStatus("IN-REVIEW", claimId);
			log.warn("The claim status changed to IN-REVIEW");

			notificationClient.notify("Your claim status is changed", claim.getCustomerId(), policy.getPolicyId());
		} else {
			repository.updateClaimStatus("REJECTED", claimId);
			log.warn("The claim status changed to Rejected");

			notificationClient.notify("Your claim validity period is expired", claim.getCustomerId(),
					policy.getPolicyId());
		}
		Optional<Claim> optionalClaim = repository.findById(claimId);
		if (optionalClaim.isPresent()) {
			return optionalClaim.get();
		} else {
			throw new ClaimNotFoundException("The claim is not present");
		}
	}

	/**
	 * Retrieves a claim by its ID.
	 *
	 * @param claimId the ID of the claim
	 * @return the claim object
	 * @throws ClaimNotFoundException if the claim is not found
	 */
	@Override
	public Claim getClaimById(long claimId) throws ClaimNotFoundException {
		log.info("Inside service implementation of Get claim by Id module");
		Optional<Claim> optional = repository.findById(claimId);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ClaimNotFoundException("Enter a Valid Claim Id...");
		}
	}

	/**
	 * Retrieves all claims from the database.
	 *
	 * @return list of all claims
	 */
	@Override
	public List<Claim> getAllClaims() {
		log.info("Inside service implementation of Get all claims module");
		return repository.findAll();
	}

	/**
	 * Deletes a claim by its ID.
	 *
	 * @param claimId the ID of the claim to delete
	 * @return confirmation message
	 */
	@Override
	public String deleteClaimById(long claimId) {
		log.info("Inside service implementation of Delete claim By Id module");
		repository.deleteById(claimId);
		log.info("Claim is deleted by Module");
		return "Claim deleted Successfully";
	}
}
