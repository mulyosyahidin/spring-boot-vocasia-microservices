package com.vocasia.enrollment.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "course")
public interface CourseFeignClient {

    @GetMapping(value = "/api/courses/{courseId}", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> getCourseById(@PathVariable("courseId") Long courseId);

}
