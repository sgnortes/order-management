package com.sgnortes.ordermanagement.common.properties.pojos;

import lombok.Getter;
import lombok.Setter;


public class ServerInfo {
    private String url;
    private String description;

    public String getUrl() {
        return url;
    }

    // Getters & Setters without Lombok because it gives an error when compiling
    // TODO: investigate how to use Getter and Setter annotation from Lombok without giving a compiling error
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
