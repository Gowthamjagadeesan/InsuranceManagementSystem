package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.demo.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
