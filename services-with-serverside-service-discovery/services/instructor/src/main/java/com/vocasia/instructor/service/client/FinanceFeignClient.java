package com.vocasia.instructor.service.client;

import com.vocasia.instructor.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "finance", url = "http://finance:14127")
public interface FinanceFeignClient {

    @GetMapping("/api/instructor/overview")
    public ResponseEntity<ResponseDto> getInstructorOverview(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                             @RequestHeader("X-INSTRUCTOR-ID") Long instructorId);

}
