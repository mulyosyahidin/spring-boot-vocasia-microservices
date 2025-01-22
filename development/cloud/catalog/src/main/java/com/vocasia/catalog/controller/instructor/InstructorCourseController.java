package com.vocasia.catalog.controller.instructor;

import com.vocasia.catalog.dto.ResponseDto;
import com.vocasia.catalog.entity.Course;
import com.vocasia.catalog.mapper.CourseMapper;
import com.vocasia.catalog.request.course.StoreCourseRequest;
import com.vocasia.catalog.service.ICourseService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
public class InstructorCourseController {

    private final Logger logger = LoggerFactory.getLogger(InstructorCourseController.class);

    private final ICourseService courseService;

    public InstructorCourseController(ICourseService iCourseService) {
        this.courseService = iCourseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseDto> getCourses(@RequestHeader("vocasia-correlation-id") String correlationId) {
        logger.info("InstructorCourseController.getCourses called");

        List<Course> courses = courseService.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courses.stream().map(CourseMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @PostMapping("/courses")
    public ResponseEntity<ResponseDto> saveCourse(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                  @Valid @RequestBody StoreCourseRequest storeCourseRequest) {
        logger.info("InstructorCourseController.saveCourse called");

        Map<String, Object> response = new HashMap<>();

        try {
            Course course = courseService.save(storeCourseRequest);

            response.put("course", course);
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal menambahkan data kursus", null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan data kursus", response, null));
    }

}
