package com.vocasia.enrollment.controller;

import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.dto.client.course.CourseDto;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.mapper.EnrollmentMapper;
import com.vocasia.enrollment.service.ICourseService;
import com.vocasia.enrollment.service.IEnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final IEnrollmentService enrollmentService;

    public CourseController(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseDto> getUserEnrolledCourses(@RequestHeader("X-USER-ID") Long userId) {
        List<Enrollment> enrollments = enrollmentService.getUserEnrolledCourse(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("enrollments", enrollments.stream().map(EnrollmentMapper::mapToDto));


        return ResponseEntity.ok().body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @GetMapping("/courses/{enrollmentId}")
    public ResponseEntity<ResponseDto> getCourseDetail(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long enrollmentId) {
       Enrollment enrollment = enrollmentService.getEnrollmentDetail(userId, enrollmentId);

        Map<String, Object> response = new HashMap<>();
        response.put("enrollment", EnrollmentMapper.mapToDto(enrollment));

        return ResponseEntity.ok().body(new ResponseDto(true, "Berhasil mendapatkan detail kursus", response, null));
    }

}
