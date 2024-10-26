package com.vocasia.qna.service.client;

import com.vocasia.qna.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication", url = "http://authentication:14120")
public interface AuthenticationFeignClient {

    @GetMapping(value = "/api/user/{userId}", consumes = "application/json")
    public ResponseEntity<ResponseDto> findUserById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @PathVariable("userId") Long userId);

}
