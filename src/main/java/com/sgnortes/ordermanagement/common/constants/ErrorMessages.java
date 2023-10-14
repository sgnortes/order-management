package com.sgnortes.ordermanagement.common.constants;

/**
 * Class to define error messages
 * @author Sergio
 */
public enum ErrorMessages {
    ENTITY_EXISTS("Entity already exists."),
    ENTITY_NOT_EXISTS("Entity doesn't exist."),
    INVALID_METHOD_ARGUMENT("Method arguments aren't valid."),
    NUMBER_PAGE_NOT_VALID("Number of page isn't valid. It should be equal or higher to 0."),
    SIZE_PAGE_NOT_VALID("Size of page isn't valid. It should be equal or higher to 0."),
    ORDER_DOESNT_EXIST("There isn't any order applied. At least one order criteria should be defined."),
    ORDER_NOT_VALID("Ordering introduced isn't valid. It should have the following format: 'fieldName,order'. Where order can be 'asc' for ascending, or 'desc' for descending."),
    ORDER_NOT_SUPPORTED("Order you tried is not supported. It should de 'asc' for ascending order, or 'desc' for descending."),
    SPECIFICATION_NOT_SUPPORTED("Operation you tried to do is not supported yet.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}