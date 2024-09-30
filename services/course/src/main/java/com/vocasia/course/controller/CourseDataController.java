package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Course Data Controller", description = "Controller untuk data kursus")
public class CourseDataController {

    private final Logger logger = LoggerFactory.getLogger(CourseDataController.class);

    private final ICourseService courseService;

    public CourseDataController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(
            summary = "Mendapatkan data kursus berdasarkan ID",
            description = "Mengambil data kursus berdasarkan ID yang diberikan"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil mendapatkan data"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @GetMapping("/data/{courseId}")
    public ResponseEntity<ResponseDto> getCourseData(@PathVariable Long courseId) {
        Course course = courseService.findById(courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));

        return ResponseEntity.ok(new ResponseDto(true, "Data kursus berhasil didapatkan", response, null));
    }

    @Operation(
            summary = "Mendapatkan draft kursus",
            description = "Mengambil draft kursus yang dimiliki oleh instruktur atau semua instruktur"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil mendapatkan data"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @GetMapping("/get/draft")
    public ResponseEntity<ResponseDto> getDraft(@RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId) {
        List<Course> courses = List.of();

        if (instructorId != null) {
            courses = courseService.getDraftCoursesByInstructorId(instructorId);
        } else {
            courses = courseService.getDraftCourses();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courses.stream().map(CourseMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Data draft kursus berhasil didapatkan", response, null));
    }
}
