package com.sgnortes.ordermanagement.common.security.service;

import com.sgnortes.ordermanagement.common.security.entity.AuthRequestDto;

/**
 * Service for token creation
 * @author Sergio
 */
public interface TokenGeneratorService {

    /**
     * Method used to create new tokens
     * @param authRequest
     * @return the token
     */
    String generateToken(AuthRequestDto authRequest);
}
