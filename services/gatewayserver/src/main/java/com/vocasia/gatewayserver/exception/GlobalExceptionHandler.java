package com.vocasia.gatewayserver.exception;

import com.vocasia.gatewayserver.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.BAD_REQUEST.value());
        data.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        ResponseDto responseDto = new ResponseDto(false,  ex.getMessage(), data, null);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseDto> handleGlobalException(Exception ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        data.put("error", ex.getMessage());

        ResponseDto responseDto = new ResponseDto(false, "Internal server error", data, null);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
