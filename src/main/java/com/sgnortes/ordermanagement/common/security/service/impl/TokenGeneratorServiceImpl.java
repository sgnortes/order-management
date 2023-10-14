package com.sgnortes.ordermanagement.common.security.service.impl;

import com.sgnortes.ordermanagement.common.security.entity.AuthRequestDto;
import com.sgnortes.ordermanagement.common.security.service.JwtService;
import com.sgnortes.ordermanagement.common.security.service.TokenGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation for token creation
 * @author Sergio
 */
@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateToken(AuthRequestDto authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }


}
