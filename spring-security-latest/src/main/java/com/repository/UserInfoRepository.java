package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.UserInfo;

import jakarta.transaction.Transactional;

@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);
    


    @Modifying


    @Query("DELETE FROM UserInfo u WHERE u.name = :name AND u.roles = :role")

 	public abstract int  deleteByName(String name,String role);


}
