package com.sgnortes.ordermanagement.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Class thar reads custom security configuration from properties file
 * @author Sergio
 */
@Component
@ConfigurationProperties(prefix = "custom-security")
public class SecurityProperties {

    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
