package com.vocasia.enrollment.controller.student;

import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.entity.CourseReview;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.mapper.CourseReviewMapper;
import com.vocasia.enrollment.request.PostCourseReviewRequest;
import com.vocasia.enrollment.service.ICourseReview;
import com.vocasia.enrollment.service.IEnrollmentService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
@Validated
public class CourseReviewController {

    private final Logger logger = LoggerFactory.getLogger(CourseReviewController.class);

    private final ICourseReview courseReviewService;
    private final IEnrollmentService enrollmentService;

    public CourseReviewController(ICourseReview iCourseReviewService, IEnrollmentService iEnrollmentService) {
        this.courseReviewService = iCourseReviewService;
        this.enrollmentService = iEnrollmentService;
    }

    @PostMapping("/courses/{enrollmentId}/review")
    public ResponseEntity<ResponseDto> postReview(@PathVariable("enrollmentId") Long enrollmentId,
                                                  @Valid @RequestBody PostCourseReviewRequest request) {
        logger.info("CourseReviewController.postReview called");

        Enrollment enrollment = enrollmentService.findById(enrollmentId);
        CourseReview courseReview = courseReviewService.findByEnrollmentId(enrollmentId);

        if (courseReview == null) {
            courseReviewService.save(enrollment, request);
        }

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan data review", null, null));
    }

    @GetMapping("/courses/{enrollmentId}/my-review")
    public ResponseEntity<ResponseDto> getMyReview(@PathVariable("enrollmentId") Long enrollmentId) {
        logger.info("CourseReviewController.getMyReview called");

        CourseReview courseReview = courseReviewService.findByEnrollmentId(enrollmentId);

        Map<String, Object> response = new HashMap<>();

        if (courseReview != null) {
            response.put("review", CourseReviewMapper.mapToDto(courseReview));
        }
        else {
            response.put("review", null);
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data review", response, null));
    }

}
