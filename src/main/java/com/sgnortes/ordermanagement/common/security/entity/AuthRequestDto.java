package com.sgnortes.ordermanagement.common.security.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Class with the info to get logged in
 * @author Sergio
 */
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
      
	private String username; 
    private String password;
    
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
  
}