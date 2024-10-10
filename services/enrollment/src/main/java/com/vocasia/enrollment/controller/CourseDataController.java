package com.vocasia.enrollment.controller;

import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.entity.client.User;
import com.vocasia.enrollment.mapper.EnrollmentMapper;
import com.vocasia.enrollment.mapper.client.UserMapper;
import com.vocasia.enrollment.service.IEnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourseDataController {

    private final IEnrollmentService enrollmentService;

    public CourseDataController(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/course-data/{courseId}/students")
    public ResponseEntity<ResponseDto> getStudents(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                   @PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentService.getUserEnrolledCourseByCourseId(correlationId, courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("enrollments", enrollments.stream().map(EnrollmentMapper::mapToDto));

        return ResponseEntity.ok().body(new ResponseDto(true, "Berhasil mendapatkan data siswa", response, null));
    }

}
