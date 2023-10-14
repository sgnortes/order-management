package com.sgnortes.ordermanagement.common.handler;

import com.sgnortes.ordermanagement.common.constants.ErrorMessages;
import com.sgnortes.ordermanagement.common.dto.CustomFieldErrorResponseDto;
import com.sgnortes.ordermanagement.common.dto.SimpleErrorResponseDto;
import com.sgnortes.ordermanagement.common.dto.ValidationErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler created to customize some of the errors shown into a more readable format.
 * @Author Sergio
 */
@ControllerAdvice
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method created to customize method argument validation error messages into more readable ones.
     *
     * @param ex - exception
     * @param headers - headers
     * @param status - http status code
     * @param request -  web request
     * @return ResponseEntity object
     */
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        // Process field errors and return customize response
        List<ValidationErrorDto> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(error -> new ValidationErrorDto(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new CustomFieldErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ErrorMessages.INVALID_METHOD_ARGUMENT.getMessage(), fieldErrors));
    }

    /**
     * Method created to convert default 500 error (internal server error) due to IllegalArgumentException into a 400 error (bad request).
     *
     * @param e an illegal argument exception
     * @return ResponseEntity object
     */
    @ExceptionHandler({ IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new SimpleErrorResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }


}
