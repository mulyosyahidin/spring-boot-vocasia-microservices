package com.vocasia.instructor.service.client;

import com.vocasia.instructor.request.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "authentication")
public interface AuthenticationFeignClient {

    @PostMapping(value = "/api/register", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> register(@RequestHeader("vocasia-correlation-id") String correlationId, @RequestBody RegisterRequest registerRequest);

    @GetMapping(value = "/api/user/{id}", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id);

}
