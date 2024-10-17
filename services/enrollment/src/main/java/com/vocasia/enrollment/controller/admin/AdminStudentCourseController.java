package com.vocasia.enrollment.controller.admin;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/student")
public class AdminStudentCourseController {

    private final Logger logger = LoggerFactory.getLogger(AdminStudentCourseController.class);

    private final IEnrollmentService enrollmentService;
    private final ICourseService courseService;

    public AdminStudentCourseController(IEnrollmentService enrollmentService, ICourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseDto> getAllStudentEnrolledCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                              @RequestHeader("X-USER-ID") Long userId,
                                                              @RequestParam(defaultValue = "1") int page) {
        logger.info("StudentCourseController.getUserEnrolledCourses called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Enrollment> enrollments = enrollmentService.findAllByUserId(userId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> enrollmentsData;

        try {
            enrollmentsData = enrollments.getContent().stream().map(enrollment -> {
                Map<String, Object> enrollmentData = new HashMap<>();

                CourseDto courseServiceFindById = courseService.findById(enrollment.getCourseId(), correlationId);

                enrollmentData.put("enrollment", EnrollmentMapper.mapToDto(enrollment));
                enrollmentData.put("course", courseServiceFindById);

                return enrollmentData;
            }).toList();

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

        pagination.put("total_page", enrollments.getTotalPages());
        pagination.put("per_page", enrollments.getSize());
        pagination.put("current_page", enrollments.getNumber() + 1);
        pagination.put("total_items", enrollments.getTotalElements());

        response.put("data", enrollmentsData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data enrollment kursus siswa", response, null));
    }

}
