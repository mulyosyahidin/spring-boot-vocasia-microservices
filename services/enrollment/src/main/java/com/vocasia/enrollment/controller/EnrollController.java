package com.vocasia.enrollment.controller;

import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.mapper.EnrollmentMapper;
import com.vocasia.enrollment.request.EnrollNewCourseRequest;
import com.vocasia.enrollment.service.IEnrollmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Enroll Controller", description = "Controller untuk melakukan enroll ke course")
public class EnrollController {

    private final Logger logger = LoggerFactory.getLogger(EnrollController.class);

    private final IEnrollmentService enrollmentService;

    public EnrollController(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/enroll")
    public ResponseEntity<ResponseDto> enroll(@Valid @RequestBody EnrollNewCourseRequest enrollNewCourseRequest) {
        List<Enrollment> enrollments = enrollmentService.enrollCourse(enrollNewCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("enrollments", enrollments.stream().map(EnrollmentMapper::mapToDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(true, "Berhasil menyimpan data enrollment", response, null));
    }

}
