package com.cts.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cts.demo.model.Claim;

import jakarta.transaction.Transactional;

//Marks this interface as a transactional component, ensuring that operations are executed within a transaction context
@Transactional

//Extends JpaRepository to provide CRUD operations for the Claim entity with Long as the ID type
public interface ClaimRepository extends JpaRepository<Claim, Long> {

	// Custom JPQL query to update the claim status for a specific claim ID
	// The @Query annotation defines the update operation using JPQL (Java
	// Persistence Query Language)
	@Query("UPDATE Claim c SET c.claimStatus = ?1 WHERE c.claimId = ?2")

	// Indicates that the query modifies the database (not a SELECT query)
	@Modifying

	// Abstract method to be implemented by Spring Data JPA to execute the update
	public abstract int updateClaimStatus(String status, long claimId);
}
