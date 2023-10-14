package com.sgnortes.ordermanagement.common.security.controller;

import com.sgnortes.ordermanagement.common.dto.PageDto;
import com.sgnortes.ordermanagement.common.security.service.TokenGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sgnortes.ordermanagement.common.security.entity.AuthRequestDto;
import com.sgnortes.ordermanagement.common.security.entity.UserInfo;
import com.sgnortes.ordermanagement.common.security.service.impl.UserDetailsServiceImpl;

@Tag(name = "Users Auth API V1", description = "Endpoints to manage authentication and authorization of the users that will use the application")
@RestController
@RequestMapping("/auth/v1")
public class AuthController {

    private final UserDetailsServiceImpl userInfoserviceImpl;

    private final TokenGeneratorService tokenGeneratorService;

    public AuthController(UserDetailsServiceImpl userInfoserviceImpl, TokenGeneratorService tokenGeneratorService) {
        this.userInfoserviceImpl = userInfoserviceImpl;
        this.tokenGeneratorService = tokenGeneratorService;
    }

    @Operation(summary = "Creates a new user in the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request",content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer/s not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/addNewUser") 
    public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfoserviceImpl.addUser(userInfo));
    }

    @Operation(summary = "Creates jwt token for the user registered in the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token generated", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request",content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer/s not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/generateToken") 
    public String authenticateAndGetToken(@RequestBody AuthRequestDto authRequest) {
        return tokenGeneratorService.generateToken(authRequest);
    } 
  
} 
