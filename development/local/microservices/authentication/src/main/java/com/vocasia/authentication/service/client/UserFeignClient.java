package com.vocasia.authentication.service.client;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.request.client.UserProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user")
public interface UserFeignClient {

    @PostMapping("/api/profile")
    ResponseEntity<ResponseDto> getUserProfile(@RequestHeader("vocasia-correlation-id") String correlationId,
                                               @RequestBody UserProfileRequest userProfileRequest);

    @GetMapping("/api/instructor-profile/{instructorId}")
    ResponseEntity<ResponseDto> getInstructorById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                  @PathVariable("instructorId") Long instructorId);

}
