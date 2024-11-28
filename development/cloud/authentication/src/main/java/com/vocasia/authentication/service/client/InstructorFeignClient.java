package com.vocasia.authentication.service.client;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.service.client.fallback.InstructorFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "instructor", url = "http://instructor:8007", fallback = InstructorFeignFallback.class)
public interface InstructorFeignClient {

    @GetMapping(value = "/api/profile-id/{userId}", consumes = "application/json")
    ResponseEntity<ResponseDto> findByUserId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable("userId") Long userId);

}
