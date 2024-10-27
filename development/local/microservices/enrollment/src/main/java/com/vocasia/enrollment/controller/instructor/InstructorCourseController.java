package com.vocasia.enrollment.controller.instructor;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
public class InstructorCourseController {

    private final Logger logger = LoggerFactory.getLogger(InstructorCourseController.class);

    private final IAuthenticationService authenticationService;
    private final IEnrollmentService enrollmentService;
    private final IPaymentService paymentService;

    public InstructorCourseController(IAuthenticationService iAuthenticationService, IEnrollmentService iEnrollmentService, IPaymentService iPaymentService) {
        this.authenticationService = iAuthenticationService;
        this.enrollmentService = iEnrollmentService;
        this.paymentService = iPaymentService;
    }

    @GetMapping("/courses/{courseId}/students")
    public ResponseEntity<ResponseDto> getStudents(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                   @PathVariable Long courseId,
                                                   @RequestParam(defaultValue = "1") int page) {
        logger.info("InstructorCourseController.getStudents called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Enrollment> enrollments = enrollmentService.findAllByCourseId(courseId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> enrollmentsData;

        try {
            enrollmentsData = enrollments.getContent().stream().map(enrollment -> {
                Map<String, Object> enrollmentData = new HashMap<>();

                UserDto findUserById = authenticationService.findUserById(enrollment.getUserId(), correlationId);
                PaymentDto payment = paymentService.findPaymentByOrderId(enrollment.getOrderId(), correlationId);

                enrollmentData.put("user", findUserById);
                enrollmentData.put("payment", payment);
                enrollmentData.put("enrollment", EnrollmentMapper.mapToDto(enrollment));

                return enrollmentData;
            }).toList();
        }
        catch (CustomFeignException e) {
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

        pagination.put("total_page", enrollments.getTotalPages());
        pagination.put("per_page", enrollments.getSize());
        pagination.put("current_page", enrollments.getNumber() + 1);
        pagination.put("total_items", enrollments.getTotalElements());

        response.put("data", enrollmentsData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data siswa", response, null));
    }

}
