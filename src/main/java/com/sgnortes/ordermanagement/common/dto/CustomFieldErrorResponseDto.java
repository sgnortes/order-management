package com.sgnortes.ordermanagement.common.dto;

import java.util.List;

/**
 * Class for customize field error response error responses
 * @author Sergio
 */
public class CustomFieldErrorResponseDto extends SimpleErrorResponseDto{

    private List<ValidationErrorDto> validationErrors;

    // TODO: investigate why lombok annotations don't work
    public CustomFieldErrorResponseDto(int status, String message, List<ValidationErrorDto> validationErrors) {
        super(status, message);
        this.validationErrors = validationErrors;
    }

    public List<ValidationErrorDto> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationErrorDto> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
