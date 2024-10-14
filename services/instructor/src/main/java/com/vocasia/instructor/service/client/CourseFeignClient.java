package com.vocasia.instructor.service.client;

import com.vocasia.instructor.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "course")
public interface CourseFeignClient {

    @GetMapping("/api/instructor/courses/{courseId}")
    public ResponseEntity<ResponseDto> findById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @PathVariable Long courseId);

}
