package com.sgnortes.ordermanagement.common.security.repository.interfaces.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgnortes.ordermanagement.common.security.entity.UserInfo;

/**
 * JPA UserInfoRepository interface
 * @author Sergio
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> { 
    Optional<UserInfo> findByName(String username); 
}