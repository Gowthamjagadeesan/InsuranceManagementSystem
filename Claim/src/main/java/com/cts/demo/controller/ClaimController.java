package com.cts.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.demo.model.Claim;
import com.cts.demo.service.ClaimService;

@RestController
@RequestMapping("/claim")
public class ClaimController {

	@Autowired
	ClaimService service;

	@PostMapping("/create")
	public String save(@RequestBody Claim claim) {
		return service.saveClaim(claim);
	}
}
