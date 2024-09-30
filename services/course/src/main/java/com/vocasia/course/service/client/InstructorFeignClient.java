package com.vocasia.course.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "instructor")
public interface InstructorFeignClient {

    @GetMapping(value = "/api/profile/{id}", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> getInstructorById(@PathVariable("id") Long id);

}

