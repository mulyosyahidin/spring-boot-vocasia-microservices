package com.vocasia.enrollment.controller;

import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.request.EnrollNewCourseRequest;
import com.vocasia.enrollment.service.IEnrollmentService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EnrollController {

    private final Logger logger = LoggerFactory.getLogger(EnrollController.class);

    private final IEnrollmentService enrollmentService;

    public EnrollController(IEnrollmentService iEnrollmentService) {
        this.enrollmentService = iEnrollmentService;
    }

    @PostMapping("/enroll")
    public ResponseEntity<ResponseDto> enroll(@RequestHeader("vocasia-correlation-id") String correlationId,
                                              @Valid @RequestBody EnrollNewCourseRequest enrollNewCourseRequest) {
        logger.info("EnrollController.enroll called");

        enrollmentService.enrollCourse(enrollNewCourseRequest);

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyimpan data enrollment", null, null));
    }

    @GetMapping("/courses/{courseId}/is-user-enrolled")
    public ResponseEntity<ResponseDto> isUserEnrolled(@RequestHeader("X-USER-ID") Long userId,
                                                      @PathVariable("courseId") Long courseId) {
        logger.info("EnrollController.isUserEnrolled called");

        boolean isUserEnrolled = enrollmentService.isUserEnrolled(userId, courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("is_user_enrolled", isUserEnrolled);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil melakukan pemeriksaan", response, null));
    }

}
