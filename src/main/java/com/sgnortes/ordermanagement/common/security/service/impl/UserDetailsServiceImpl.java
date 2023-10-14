package com.sgnortes.ordermanagement.common.security.service.impl;
import com.sgnortes.ordermanagement.common.security.entity.UserInfo;
import com.sgnortes.ordermanagement.common.security.entity.impl.UserInfoDetails;
import com.sgnortes.ordermanagement.common.security.repository.interfaces.jpa.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service; 
  
import java.util.Optional;

/**
 * UserDetailsService implementation
 * @author Sergio
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
  
        Optional<UserInfo> userDetail = repository.findByName(username); 
  
        return userDetail.map(UserInfoDetails::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
    }

    /**
     * Method that adds a user to the database.
     * @param userInfo
     * @return UserInfo object with user's data
     */
    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
        return repository.save(userInfo);
    }

} 