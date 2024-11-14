package com.vocasia.finance.service.client;

import com.vocasia.finance.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "course", url = "http://course:14122")
public interface CourseFeignClient {

    @GetMapping(value = "/api/instructor/courses/{courseId}", consumes = "application/json")
    public ResponseEntity<ResponseDto> findById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                @PathVariable("courseId") Long courseId);

}
