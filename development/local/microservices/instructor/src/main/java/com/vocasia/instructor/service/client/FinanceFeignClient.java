package com.vocasia.instructor.service.client;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.service.client.fallback.FinanceFeignCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "finance", fallback = FinanceFeignCallback.class)
public interface FinanceFeignClient {

    @GetMapping("/api/instructor/overview")
    ResponseEntity<ResponseDto> getInstructorOverview(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @RequestHeader("X-INSTRUCTOR-ID") Long instructorId);

}
