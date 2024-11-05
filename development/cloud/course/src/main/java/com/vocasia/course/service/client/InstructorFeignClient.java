package com.vocasia.course.service.client;

import com.vocasia.course.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "instructor")
public interface InstructorFeignClient {

    @GetMapping(value = "/api/profile/{id}", consumes = "application/json")
    public ResponseEntity<ResponseDto> findById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                @PathVariable("id") Long id);

}
