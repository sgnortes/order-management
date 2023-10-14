package com.sgnortes.ordermanagement.common.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

/**
 * Service with common methods to work with JWT Tokens
 * @author Sergio
 */
public interface JwtService {

    /**
     * Method that creates a token for the userName passed as a parameter
     * @param userName
     * @return the token
     */
    String generateToken(String userName);

    /**
     * Method used to extract the userName from the token
     * @param token
     * @return the userName
     */
    String extractUsername(String token);

    /**
     * Method used to extract the expiration of the token
     * @param token
     * @return Date of expiration
     */
    Date extractExpiration(String token);

    /**
     * Method used to extract the claim
     * @param token
     * @param claimsResolver
     * @return
     * @param <T>
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Method used to validate the token with userDetails passed as a parameter
     * @param token
     * @param userDetails
     * @return boolean value that indicates validness of the token
     */
    Boolean validateToken(String token, UserDetails userDetails);
}
