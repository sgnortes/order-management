package com.sgnortes.ordermanagement.common.properties;

import com.sgnortes.ordermanagement.common.properties.pojos.ServerInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class that reads swagger configuration from properties files
 * @author Sergio
 */
@Component
@ConfigurationProperties(prefix = "swagger-custom-properties")
public class SwaggerCustomProperties {
    private List<ServerInfo> serversInfo;
    private String contactEmail;
    private String contactName;
    private String apiTitle;
    private String apiVersion;
    private String apiDescription;

    // Getters & Setters without Lombok because it gives an error when compiling
    // TODO: investigate how to use Getter and Setter annotation from Lombok without giving a compiling error
    public List<ServerInfo> getServersInfo() {
        return serversInfo;
    }

    public void setServersInfo(List<ServerInfo> serversInfo) {
        this.serversInfo = serversInfo;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getApiTitle() {
        return apiTitle;
    }

    public void setApiTitle(String apiTitle) {
        this.apiTitle = apiTitle;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }
}
