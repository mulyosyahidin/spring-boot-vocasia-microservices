package com.vocasia.course.exception;

import com.vocasia.course.dto.ResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });

        ResponseDto responseDto = new ResponseDto(false, "Validation error", null, errors);
        return new ResponseEntity<>(responseDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.BAD_REQUEST.value());
        data.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        ResponseDto responseDto = new ResponseDto(false,  ex.getMessage(), data, null);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.CONFLICT.value());
        data.put("error", ex.getMessage());

        ResponseDto responseDto = new ResponseDto(false, "Data integrity violation", data, null);
        return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.NOT_FOUND.value());
        data.put("error", ex.getMessage());

        ResponseDto responseDto = new ResponseDto(false, "Resource not found", data, null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseDto> handleNoResourceFoundException(NoResourceFoundException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.NOT_FOUND.value());
        data.put("error", ex.getMessage());

        ResponseDto responseDto = new ResponseDto(false, "Resource tidak ada", data, null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ResponseDto> handleCustomFeignException(CustomFeignException ex) {
        ResponseDto responseDto = new ResponseDto(false, ex.getMessage(), null, ex.getErrors());

        return ResponseEntity.unprocessableEntity().body(responseDto);
    }

}
