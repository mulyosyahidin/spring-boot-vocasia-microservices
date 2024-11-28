package com.vocasia.order.service.client;

import com.vocasia.order.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication", url = "http://authentication:8001")
public interface AuthenticationFeignClient {

    @GetMapping(value = "/api/user/{userId}", consumes = "application/json")
    ResponseEntity<ResponseDto> findUserById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @PathVariable("userId") Long userId);

}
