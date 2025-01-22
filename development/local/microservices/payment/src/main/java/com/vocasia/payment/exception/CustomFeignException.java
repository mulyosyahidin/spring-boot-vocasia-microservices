package com.vocasia.payment.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.Getter;

import java.io.IOException;
import java.util.Map;

@Getter
public class CustomFeignException extends RuntimeException {
    private final Boolean success;
    private final Object errors;
    private final int httpStatus;

    public CustomFeignException(Boolean success, String message, Object errors, int httpStatus) {
        super(message);
        this.success = success;
        this.errors = errors;
        this.httpStatus = httpStatus;
    }

    public CustomFeignException(FeignException e, ObjectMapper objectMapper) {
        super(parseMessage(e, objectMapper));
        Map<String, Object> responseBody = parseResponseBody(e, objectMapper);

        this.success = responseBody != null && responseBody.get("success") != null
                ? (Boolean) responseBody.get("success")
                : false;
        this.errors = responseBody != null ? extractError(responseBody) : null;
        this.httpStatus = e.status();
    }

    private static String parseMessage(FeignException e, ObjectMapper objectMapper) {
        try {
            Map<String, Object> responseBody = objectMapper.readValue(e.contentUTF8(), new TypeReference<Map<String, Object>>() {});
            return responseBody != null && responseBody.get("message") != null
                    ? responseBody.get("message").toString()
                    : "Unknown error";
        } catch (IOException ioException) {
            return "Failed to parse error message";
        }
    }

    private static Map<String, Object> parseResponseBody(FeignException e, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(e.contentUTF8(), new TypeReference<Map<String, Object>>() {});
        } catch (IOException ioException) {
            return null;
        }
    }

    private static Object extractError(Map<String, Object> responseBody) {
        if (responseBody.containsKey("data") && responseBody.get("data") instanceof Map) {
            Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

            if (data.containsKey("error")) {
                return data.get("error");
            }
        }

        return responseBody.get("errors");
    }
}
