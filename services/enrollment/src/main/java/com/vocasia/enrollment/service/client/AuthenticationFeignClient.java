package com.vocasia.enrollment.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "authentication")
public interface AuthenticationFeignClient {

    @GetMapping(value = "/api/user/{id}", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id);

}
