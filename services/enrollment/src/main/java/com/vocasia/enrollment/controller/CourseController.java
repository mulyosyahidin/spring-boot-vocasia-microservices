package com.vocasia.enrollment.controller;

import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.dto.client.course.CourseDto;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.exception.CustomFeignException;
import com.vocasia.enrollment.mapper.EnrollmentMapper;
import com.vocasia.enrollment.service.ICourseService;
import com.vocasia.enrollment.service.IEnrollmentService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final IEnrollmentService enrollmentService;
    private final ICourseService courseService;

    public CourseController(IEnrollmentService iEnrollmentService, ICourseService iCourseService) {
        this.enrollmentService = iEnrollmentService;
        this.courseService = iCourseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseDto> getUserEnrolledCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                              @RequestHeader("X-USER-ID") Long userId) {
        logger.info("CourseController.getUserEnrolledCourses called");

        List<Enrollment> enrollments = enrollmentService.getUserEnrolledCourse(userId);

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> enrollmentsData = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            Map<String, Object> enrollmentData = new HashMap<>();

            enrollmentData.put("enrollment", EnrollmentMapper.mapToDto(enrollment));

            try {
                CourseDto courseServiceFindById = courseService.findById(enrollment.getCourseId(), correlationId);

                enrollmentData.put("course", courseServiceFindById);
            } catch (CustomFeignException e) {
                logger.error(e.getMessage(), e);

                return ResponseEntity
                        .status(e.getHttpStatus())
                        .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);

                return ResponseEntity
                        .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(false, e.getMessage(), null, null));
            }

            enrollmentsData.add(enrollmentData);
        }

        response.put("enrollments", enrollmentsData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data enrollment kursus", response, null));
    }

    @GetMapping("/courses/{enrollmentId}")
    public ResponseEntity<ResponseDto> getCourseDetail(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                       @RequestHeader("X-USER-ID") Long userId, @PathVariable Long enrollmentId) {
        logger.info("CourseController.getCourseDetail called");

        Enrollment enrollment = enrollmentService.findById(userId, enrollmentId);

        Map<String, Object> response = new HashMap<>();
        response.put("enrollment", EnrollmentMapper.mapToDto(enrollment));

        try {
            CourseDto courseServiceFindById = courseService.findById(enrollment.getCourseId(), correlationId);

            response.put("course", courseServiceFindById);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan detail enrollment kursus", response, null));
    }

}
