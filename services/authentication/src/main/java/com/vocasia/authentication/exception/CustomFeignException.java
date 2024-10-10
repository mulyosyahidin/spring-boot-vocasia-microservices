package com.vocasia.authentication.exception;

public class CustomFeignException extends RuntimeException {
    public CustomFeignException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomFeignException(String message) {
        super(message);
    }
}
