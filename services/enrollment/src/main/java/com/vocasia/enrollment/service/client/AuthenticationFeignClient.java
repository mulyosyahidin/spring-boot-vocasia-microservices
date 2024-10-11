package com.vocasia.enrollment.service.client;

import com.vocasia.enrollment.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication")
public interface AuthenticationFeignClient {

    @GetMapping(value = "/api/user/{userId}", consumes = "application/json")
    public ResponseEntity<ResponseDto> findById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                @PathVariable("userId") Long userId);

}
