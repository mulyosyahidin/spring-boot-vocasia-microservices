package com.vocasia.enrollment.controller;

import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.dto.client.payment.PaymentDto;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.exception.CustomFeignException;
import com.vocasia.enrollment.mapper.EnrollmentMapper;
import com.vocasia.enrollment.service.IAuthenticationService;
import com.vocasia.enrollment.service.IEnrollmentService;
import com.vocasia.enrollment.service.IPaymentService;
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
public class CourseDataController {

    private final Logger logger = LoggerFactory.getLogger(CourseDataController.class);

    private final IAuthenticationService authenticationService;
    private final IEnrollmentService enrollmentService;
    private final IPaymentService paymentService;

    public CourseDataController(IAuthenticationService iAuthenticationService, IEnrollmentService iEnrollmentService, IPaymentService iPaymentService) {
        this.authenticationService = iAuthenticationService;
        this.enrollmentService = iEnrollmentService;
        this.paymentService = iPaymentService;
    }

    @GetMapping("/course-data/{courseId}/students")
    public ResponseEntity<ResponseDto> getStudents(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                   @PathVariable Long courseId) {
        logger.debug("CourseDataController.getStudents called");

        List<Enrollment> enrollments = enrollmentService.getUserEnrolledCourseByCourseId(correlationId, courseId);

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> enrollmentsData = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            Map<String, Object> enrollmentData = new HashMap<>();

            enrollmentData.put("enrollment", EnrollmentMapper.mapToDto(enrollment));

            try {
                UserDto findUserById = authenticationService.getByUserId(enrollment.getUserId(), correlationId);
                PaymentDto payment = paymentService.findPaymentByOrderId(enrollment.getOrderId(), correlationId);

                enrollmentData.put("user", findUserById);
                enrollmentData.put("payment", payment);
            } catch (CustomFeignException e) {
                return ResponseEntity
                        .status(e.getHttpStatus())
                        .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(false, e.getMessage(), null, null));
            }

            enrollmentsData.add(enrollmentData);
        }

        response.put("enrollments", enrollmentsData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data siswa", response, null));
    }

}
