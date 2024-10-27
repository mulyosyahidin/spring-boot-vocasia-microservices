package com.vocasia.enrollment.controller.student;

import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.entity.Progress;
import com.vocasia.enrollment.request.CompleteLessonRequest;
import com.vocasia.enrollment.request.SetLastAccessLessonRequest;
import com.vocasia.enrollment.service.IEnrollmentService;
import com.vocasia.enrollment.service.IProgressService;
import com.vocasia.enrollment.types.ProgressStatus;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentProgressController {

    private final Logger logger = LoggerFactory.getLogger(StudentProgressController.class);

    private final IEnrollmentService enrollmentService;
    private final IProgressService progressService;

    public StudentProgressController(IEnrollmentService iEnrollmentService, IProgressService iProgressService) {
        this.enrollmentService = iEnrollmentService;
        this.progressService = iProgressService;
    }

    @PostMapping("/progress/{enrollmentId}/set-last-access-lesson")
    public ResponseEntity<ResponseDto> setLastAccessLesson(@PathVariable Long enrollmentId,
                                                           @Valid @RequestBody SetLastAccessLessonRequest setLastAccessLessonRequest) {
        logger.info("StudentProgressController.setLastAccessLesson called");

        Enrollment enrollment = enrollmentService.findById(enrollmentId);
        enrollment.setLastLessonId(setLastAccessLessonRequest.getLessonId());
        enrollmentService.update(enrollment);

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil memperbarui data", null, null));
    }

    @PostMapping("/progress/{enrollmentId}/start-lesson/{lessonId}")
    public ResponseEntity<ResponseDto> startLesson(@PathVariable Long enrollmentId, @PathVariable Long lessonId) {
        logger.info("StudentProgressController.startLesson called");

        Enrollment enrollment = enrollmentService.findById(enrollmentId);
        boolean isLessonHasRecorded = progressService.existsByEnrollmentIdAndLessonId(enrollment.getId(), lessonId);

        if (!isLessonHasRecorded) {
            progressService.save(enrollment, lessonId);
        }

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil memulai lesson", null, null));
    }

    @GetMapping("/progress/{enrollmentId}/is-lesson-complete/{lessonId}")
    public ResponseEntity<ResponseDto> isLessonComplete(@PathVariable Long enrollmentId, @PathVariable Long lessonId) {
        logger.info("StudentProgressController.isLessonComplete called");

        boolean isComplete = false;

        Enrollment enrollment = enrollmentService.findById(enrollmentId);
        boolean isLessonHasRecorded = progressService.existsByEnrollmentIdAndLessonId(enrollment.getId(), lessonId);

        if (isLessonHasRecorded) {
            Progress progress = progressService.findByEnrollmentIdAndLessonId(enrollment.getId(), lessonId);

            if (progress.getStatus().equals(ProgressStatus.COMPLETED.toString())) {
                isComplete = true;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("is_complete", isComplete);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memeriksa", response, null));
    }

    @PostMapping("/progress/{enrollmentId}/complete-lesson/{lessonId}")
    public ResponseEntity<ResponseDto> completeLesson(@PathVariable Long enrollmentId,
                                                      @PathVariable Long lessonId,
                                                      @Valid @RequestBody CompleteLessonRequest completeLessonRequest) {
        logger.info("StudentProgressController.completeLesson called");

        Enrollment enrollment = enrollmentService.findById(enrollmentId);
        Progress progress = progressService.findByEnrollmentIdAndLessonId(enrollment.getId(), lessonId);

        if (progress == null) {
            return ResponseEntity
                    .status(HttpStatus.SC_OK)
                    .body(new ResponseDto(true, "Permintaan tidak dapat diproses", null, null));
        }

        Map<String, Object> response = new HashMap<>();
        boolean isCourseCompleted = false;
        BigDecimal percentage = new BigDecimal(0);

        if (progress.getStatus().equals(ProgressStatus.IN_PROGRESS.toString())) {
            progress.setStatus(ProgressStatus.COMPLETED.toString());
            progress.setCompletedAt(LocalDateTime.now());

            progressService.update(progress);

            int totalLesson = completeLessonRequest.getTotalLesson();
            int totalCompletedLesson = progressService.countByEnrollmentIdAndStatus(enrollment.getId(), ProgressStatus.COMPLETED.toString());
            percentage = new BigDecimal(totalCompletedLesson).divide(new BigDecimal(totalLesson), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));

            enrollment.setProgressPercentage(percentage);

            if (percentage.compareTo(new BigDecimal(100)) == 0) {
                enrollment.setCompletionDate(LocalDateTime.now());
            }

            enrollmentService.update(enrollment);
        }

        isCourseCompleted = percentage.compareTo(new BigDecimal(100)) == 0;

        response.put("is_course_completed", isCourseCompleted);
        response.put("percentage", percentage);

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyelesaikan pelajaran", response, null));
    }

}
