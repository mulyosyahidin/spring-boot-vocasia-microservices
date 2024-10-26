package com.vocasia.course.controller.instructor;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.service.ICourseService;
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

    private final ICourseService courseService;

    public OverviewController(ICourseService iCourseService) {
        this.courseService = iCourseService;
    }

    @GetMapping("/instructor/overview")
    public ResponseEntity<ResponseDto> getInstructorOverview(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                             @RequestHeader("X-INSTRUCTOR-ID") Long instructorId) {
        logger.info("OverviewController.getInstructorOverview called");

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> overview = new HashMap<>();

        int draft = courseService.countByStatusAndInstructorId("draft", instructorId);
        int published = courseService.countByStatusAndInstructorId("published", instructorId);
        int total = draft + published;

        overview.put("draft", draft);
        overview.put("published", published);
        overview.put("total", total);

        response.put("overview", overview);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data overview instruktur", response, null));
    }

}
