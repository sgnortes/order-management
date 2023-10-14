package com.sgnortes.ordermanagement.common.security.service.impl;

import com.sgnortes.ordermanagement.common.properties.SecurityProperties;
import com.sgnortes.ordermanagement.common.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.SignatureAlgorithm; 
import io.jsonwebtoken.io.Decoders; 
import io.jsonwebtoken.security.Keys; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.function.Function;

/**
 * Jwt Service implementation.
 * @author Sergio
 */
@Service
public class JwtServiceImpl implements JwtService {

    private final SecurityProperties securityProperties;

    public JwtServiceImpl(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>(); 
        return createToken(claims, userName); 
    }

    /**
     * Method that creates a token
     * @param claims
     * @param userName
     * @return the token
     */
    private String createToken(Map<String, Object> claims, String userName) { 
        return Jwts.builder() 
                .setClaims(claims) 
                .setSubject(userName) 
                .setIssuedAt(new Date(System.currentTimeMillis())) 
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) 
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); 
    }

    /**
     * Method that gets the sign key
     * @return the key
     */
    private Key getSignKey() { 
        byte[] keyBytes= Decoders.BASE64.decode(securityProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
        final Claims claims = extractAllClaims(token); 
        return claimsResolver.apply(claims); 
    }

    /**
     * Method used to extract all the claims from the token
     * @param token
     * @return the claims
     */
    private Claims extractAllClaims(String token) { 
        return Jwts 
                .parserBuilder() 
                .setSigningKey(getSignKey()) 
                .build() 
                .parseClaimsJws(token) 
                .getBody(); 
    }

    /**
     * Method that indicates if token has expired
     * @param token
     * @return boolean indicating if token has expired
     */
    private Boolean isTokenExpired(String token) { 
        return extractExpiration(token).before(new Date()); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateToken(String token, UserDetails userDetails) { 
        final String username = extractUsername(token); 
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); 
    } 

} 
