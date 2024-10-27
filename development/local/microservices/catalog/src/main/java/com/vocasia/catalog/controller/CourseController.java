package com.vocasia.catalog.controller;

import com.vocasia.catalog.dto.ResponseDto;
import com.vocasia.catalog.entity.Course;
import com.vocasia.catalog.mapper.CourseMapper;
import com.vocasia.catalog.service.ICategoryService;
import com.vocasia.catalog.service.ICourseService;
import jakarta.ws.rs.Path;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final ICategoryService categoryService;
    private final ICourseService courseService;

    public CourseController(ICategoryService iCategoryService, ICourseService iCourseService) {
        this.categoryService = iCategoryService;
        this.courseService = iCourseService;
    }

    @GetMapping("/courses/latests")
    public ResponseEntity<ResponseDto> getLatestCourses() {
        logger.info("CourseController.getLatestCourses called");

        List<Course> courses = courseService.findLatestCourses();

        Map<String, Object> response = new HashMap<>();
        response.put("data", courses.stream().map(CourseMapper::mapToPublicDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus terbaru", response, null));
    }

    @GetMapping("/courses/{courseSlug}/{courseId}")
    public ResponseEntity<ResponseDto> getCourseDetail(@PathVariable("courseSlug") String courseSlug, @PathVariable("courseId") Long courseId) {
        logger.info("CourseController.getCourseDetail called");

        Course course = courseService.findById(courseId);
        Map<String, Object> response = new HashMap<>();

        response.put("data", CourseMapper.mapToDto(course, true));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

}
