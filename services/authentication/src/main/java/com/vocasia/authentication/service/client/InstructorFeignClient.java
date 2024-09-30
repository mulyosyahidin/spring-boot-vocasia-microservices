package com.vocasia.authentication.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "instructor")
public interface InstructorFeignClient {

    @GetMapping(value = "/api/profile-by-user-id/{userId}", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> getInstructorByUserId(@RequestHeader("vocasia-correlation-id") String correlationId, @PathVariable("userId") Long userId);

}
