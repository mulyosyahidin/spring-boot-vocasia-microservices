package com.vocasia.instructor.exception;

import com.vocasia.instructor.dto.ValidationErrorResponse;
import lombok.Getter;

@Getter
public class UnprocessableEntityException extends RuntimeException {
    private final ValidationErrorResponse errorResponse;

    public UnprocessableEntityException(String message, ValidationErrorResponse errorResponse) {
        super(message);
        this.errorResponse = errorResponse;
    }

}

