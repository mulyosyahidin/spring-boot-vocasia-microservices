package com.vocasia.order.service.client;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.request.client.EnrollNewCourseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "enrollment")
public interface EnrollmentFeignClient {

    @PostMapping(value = "/api/enroll", consumes = "application/json")
    public ResponseEntity<ResponseDto> enrollCourse(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @RequestBody EnrollNewCourseRequest enrollNewCourseRequest);

}
