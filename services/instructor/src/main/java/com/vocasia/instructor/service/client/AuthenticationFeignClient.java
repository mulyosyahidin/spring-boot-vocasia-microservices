package com.vocasia.instructor.service.client;

import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.request.UpdateProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(name = "authentication")
public interface AuthenticationFeignClient {

    @PostMapping(value = "/api/register", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> register(@RequestHeader("vocasia-correlation-id") String correlationId, @RequestBody RegisterRequest registerRequest);

    @GetMapping(value = "/api/user/{id}", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id);

    @PutMapping(value = "/api/user/{id}/update-user", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id,
                                                          @RequestPart(value = "profile_picture", required = false) MultipartFile profilePicture,
                                                          @RequestParam("name") String name,
                                                          @RequestParam("email") String email,
                                                          @RequestParam(value = "password", required = false) String password);

}
