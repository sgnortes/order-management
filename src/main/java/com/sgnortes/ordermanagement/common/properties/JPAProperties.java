package com.sgnortes.ordermanagement.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Class that reads jpa config from properties file
 * @author Sergio
 */
@Component
@ConfigurationProperties("spring.jpa-custom-config")
public class JPAProperties {
    private Integer batchSize;

    // TODO: investigate how to use Getter and Setter annotation from Lombok without giving a compiling error

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }
}
