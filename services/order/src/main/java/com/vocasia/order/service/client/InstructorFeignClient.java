package com.vocasia.order.service.client;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.request.client.instructor.AssignCourseToStudentInstructorRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "instructor")
public interface InstructorFeignClient {

    @PostMapping("/api/assign-course")
    public ResponseEntity<ResponseDto> assignCourse(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest);

}
