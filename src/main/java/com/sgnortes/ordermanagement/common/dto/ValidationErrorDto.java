package com.sgnortes.ordermanagement.common.dto;

/**
 * Dto created to deal with validations errors in a more readable way.
 * @Author Sergio
 */
public class ValidationErrorDto {
    private String field;
    private String message;

    // TODO: investigate why lombok annotations aren't working.

    public ValidationErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
