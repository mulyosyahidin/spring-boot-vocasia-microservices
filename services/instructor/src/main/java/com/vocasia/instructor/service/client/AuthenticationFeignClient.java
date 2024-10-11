package com.vocasia.instructor.service.client;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.request.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "authentication")
public interface AuthenticationFeignClient {

    @PostMapping(value = "/api/register", consumes = "application/json")
    public ResponseEntity<ResponseDto> register(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                @RequestBody RegisterRequest registerRequest);

    @GetMapping(value = "/api/user/{id}", consumes = "application/json")
    public ResponseEntity<ResponseDto> getUserById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                   @PathVariable Long id);

    @PutMapping(value = "/api/user/{id}/update-user", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDto> updateUser(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @PathVariable Long id,
                                                          @RequestPart(value = "profile_picture", required = false) MultipartFile profilePicture,
                                                          @RequestParam("name") String name,
                                                          @RequestParam("email") String email,
                                                          @RequestParam(value = "password", required = false) String password);

}
