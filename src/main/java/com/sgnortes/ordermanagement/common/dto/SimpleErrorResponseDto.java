package com.sgnortes.ordermanagement.common.dto;

/**
 * Class for simple error responses
 * @author Sergio
 */
public class SimpleErrorResponseDto {

    private int status;
    private String message;

    // TODO: investigate why lombok annotations don't work

    public SimpleErrorResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
