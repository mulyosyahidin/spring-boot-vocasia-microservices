package com.vocasia.instructor.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ValidationErrorResponse {
    private boolean success;
    private String message;
    private Map<String, List<String>> errors;
}

