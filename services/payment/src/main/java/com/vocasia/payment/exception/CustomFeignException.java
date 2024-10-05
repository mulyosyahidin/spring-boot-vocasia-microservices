package com.vocasia.payment.exception;

import lombok.Getter;

@Getter
public class CustomFeignException extends RuntimeException {
    private final Object errors;

    public CustomFeignException(String message, Object errors) {
        super(message);
        this.errors = errors;
    }
}

