package com.cts.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.demo.model.Claim;
import com.cts.demo.repository.ClaimRepository;

@Service("claimService")
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimRepository repository;

	@Override
	public String saveClaim(Claim claim) {
		Claim c = repository.save(claim);
		if (c != null)
			return "saved Successfully";
		else
			return "problem";
	}

}
