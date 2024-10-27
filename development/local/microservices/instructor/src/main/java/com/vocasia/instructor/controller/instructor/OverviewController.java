package com.vocasia.instructor.controller.instructor;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.client.course.InstructorOverviewDto;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.service.ICourseService;
import com.vocasia.instructor.service.IFinanceService;
import com.vocasia.instructor.service.IInstructorStudentService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OverviewController {

    private final Logger logger = LoggerFactory.getLogger(OverviewController.class);

    private final IInstructorStudentService instructorStudentService;
    private final ICourseService courseService;
    private final IFinanceService financeService;

    public OverviewController(IInstructorStudentService iInstructorStudentService, ICourseService iCourseService, IFinanceService iFinanceService) {
        this.instructorStudentService = iInstructorStudentService;
        this.courseService = iCourseService;
        this.financeService = iFinanceService;
    }

    @GetMapping("/overview")
    public ResponseEntity<ResponseDto> getMyOverview(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestHeader("X-INSTRUCTOR-ID") Long instructorId) {
        logger.info("OverviewController.getMyOverview called");

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> overview = new HashMap<>();

        int countMyStudent = instructorStudentService.countByInstructorId(instructorId);
        overview.put("total_student", countMyStudent);

        try {
            com.vocasia.instructor.dto.client.course.InstructorOverviewDto courseOverviewDto = courseService.getInstructorOverview(instructorId, correlationId);
            com.vocasia.instructor.dto.client.finance.InstructorOverviewDto financeOverviewDto = financeService.getInstructorOverview(correlationId, instructorId);

            overview.put("course", courseOverviewDto);
            overview.put("finance", financeOverviewDto);
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

        response.put("overview", overview);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data overview instruktur", response, null));
    }

}
