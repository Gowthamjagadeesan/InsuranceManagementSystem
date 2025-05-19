package com.cts.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.demo.dto.Policy;
import com.cts.demo.exception.ClaimNotFoundException;
import com.cts.demo.feignclient.NotificationClient;
import com.cts.demo.feignclient.PolicyClient;
import com.cts.demo.model.Claim;
import com.cts.demo.repository.ClaimRepository;
import com.cts.demo.service.ClaimServiceImpl;

@SpringBootTest
class ClaimApplicationTests {
	@Mock
	ClaimRepository repository;

	@InjectMocks
	ClaimServiceImpl service;

	@Mock
	PolicyClient policyClient;

	@Mock
	NotificationClient notificationClient;

	@Test
	void saveTest() {
		Claim claim = new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW");
		Mockito.when(repository.save(claim)).thenReturn(claim);
		String response = service.fileClaim(claim);
		assertEquals("New claim request has been filed successfully", response);
	}

	@Test
	void reviewClaimByIdAndAmount_Approved() throws ClaimNotFoundException {
		Claim claim = new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW");
		Policy policy = new Policy(4, "Health", 100000, "hospital expenses", LocalDate.of(2025, 6, 21));
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(claim));
		Mockito.when(policyClient.retrievePolicy(4)).thenReturn(policy);
		Mockito.when(repository.updateClaimStatus("APPROVED", 1L)).thenReturn(1);
		Claim updatedClaim = new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW");
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(updatedClaim));
		Claim result = service.reviewClaimByIdAndAmount(1L);
		assertEquals("APPROVED", result.getClaimStatus());
	}

	@Test
	void reviewClaimByIdAndValidityPeriod() throws ClaimNotFoundException {
		Claim claim = new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW");
		Policy policy = new Policy(4, "Health", 100000, "hospital expenses", LocalDate.of(2025, 6, 21));
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(claim));
		Mockito.when(policyClient.retrievePolicy(4)).thenReturn(policy);
		Mockito.when(repository.updateClaimStatus("IN-REVIEW", 1L)).thenReturn(1);
		Claim updatedClaim = new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW");
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(updatedClaim));
		Claim result = service.reviewClaimByIdAndValidityPeriod(1L);
		assertEquals("IN-REVIEW", result.getClaimStatus());
	}

	@Test
	void getClaimById() throws ClaimNotFoundException {
		Claim claim = new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW");
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(claim));
		Claim result = service.getClaimById(1L);
		assertEquals(claim, result);

	}

	@Test
	void getAllClaims() {

		List<Claim> claims = Arrays.asList(new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW"),
				new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW"));
		Mockito.when(repository.findAll()).thenReturn(claims);
		List<Claim> resClaims = service.getAllClaims();
		assertEquals(claims, resClaims);

	}

	@Test
	void deleteClaimById() {
		Claim claim = new Claim(1, 2, "gowthamjagadeesan322@gmail.com", 3, 4, 70000, "IN-REVIEW");
		Mockito.doNothing().when(repository).deleteById(claim.getClaimId());
		String response = service.deleteClaimById(claim.getClaimId());
		assertEquals("Claim deleted Successfully", response);

	}

}
