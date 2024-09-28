package com.vocasia.instructor.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.instructor.dto.response.ValidationErrorResponse;
import com.vocasia.instructor.exception.UnprocessableEntityException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
            try (InputStream bodyIs = response.body().asInputStream()) {
                ValidationErrorResponse errorResponse = objectMapper.readValue(bodyIs, ValidationErrorResponse.class);
                return new UnprocessableEntityException(errorResponse.getMessage(), errorResponse);
            } catch (IOException e) {
                return new UnprocessableEntityException("Unprocessable Entity Error", null);
            }
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}

