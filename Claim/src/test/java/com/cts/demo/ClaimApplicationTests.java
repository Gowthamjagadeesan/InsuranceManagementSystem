package com.cts.demo;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.demo.repository.ClaimRepository;
import com.cts.demo.service.ClaimServiceImpl;

@SpringBootTest
class ClaimApplicationTests {
	@Mock
	ClaimRepository repository;

	@InjectMocks
	ClaimServiceImpl service;
	
	

}
