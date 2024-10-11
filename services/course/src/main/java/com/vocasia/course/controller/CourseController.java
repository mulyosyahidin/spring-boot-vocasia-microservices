package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.dto.feign.InstructorDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Course;
import com.vocasia.course.exception.CustomFeignException;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;
import com.vocasia.course.service.ICourseService;
import com.vocasia.course.service.IInstructorService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final ICourseService courseService;
    private final IInstructorService instructorService;

    public CourseController(ICourseService iCourseService, IInstructorService iInstructorService) {
        this.courseService = iCourseService;
        this.instructorService = iInstructorService;
    }

    @GetMapping("/courses/all")
    public ResponseEntity<ResponseDto> getAllCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId) {
        logger.debug("CourseController.getAllCourses called");

        List<Course> courses = List.of();

        if (instructorId != null) {
            courses = courseService.getAllCoursesByInstructorId(instructorId);
        } else {
            courses = courseService.getAllCourses();
        }

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> coursesData = new ArrayList<>();

        for (Course course : courses) {
            Map<String, Object> courseData = new HashMap<>();

            courseData.put("course", CourseMapper.mapToDto(course));
            courseData.put("category", CategoryMapper.mapToDto(course.getCategory(), false));
            courseData.put("instructor", instructorService.getInstructorById(course.getInstructorId(), correlationId));

            coursesData.add(courseData);
        }

        response.put("courses", coursesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @GetMapping("/courses/published")
    public ResponseEntity<ResponseDto> getAllPublishedCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                              @RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId) {
        logger.debug("CourseController.getAllPublishedCourses called");

        List<Course> courses = List.of();

        if (instructorId != null) {
            courses = courseService.getPublishedCoursesByInstructorId(instructorId);
        } else {
            courses = courseService.getPublishedCourses();
        }

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> coursesData = new ArrayList<>();

        for (Course course : courses) {
            Map<String, Object> courseData = new HashMap<>();

            courseData.put("course", CourseMapper.mapToDto(course));
            courseData.put("category", CategoryMapper.mapToDto(course.getCategory(), false));
            courseData.put("instructor", instructorService.getInstructorById(course.getInstructorId(), correlationId));

            coursesData.add(courseData);
        }

        response.put("courses", coursesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @GetMapping("/courses/draft")
    public ResponseEntity<ResponseDto> getAllDraftCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId) {
        logger.debug("CourseController.getAllDraftCourses called");

        List<Course> courses = List.of();

        if (instructorId != null) {
            courses = courseService.getDraftCoursesByInstructorId(instructorId);
        } else {
            courses = courseService.getDraftCourses();
        }

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> coursesData = new ArrayList<>();

        for (Course course : courses) {
            Map<String, Object> courseData = new HashMap<>();

            courseData.put("course", CourseMapper.mapToDto(course));
            courseData.put("category", CategoryMapper.mapToDto(course.getCategory(), false));
            courseData.put("instructor", instructorService.getInstructorById(course.getInstructorId(), correlationId));

            coursesData.add(courseData);
        }

        response.put("courses", coursesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @PostMapping("/courses")
    public ResponseEntity<ResponseDto> createCourse(@Valid @RequestBody CreateNewCourseRequest createNewCourseRequest) {
        logger.debug("CourseController.createCourse called");

        Course course = courseService.save(createNewCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan kursus baru", response, null));
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<ResponseDto> getCourseById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @PathVariable Long courseId) {
        logger.debug("CourseController.getCourseById called");

        Course course = courseService.findById(courseId);
        Category courseCategory = course.getCategory();

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));
        response.put("category", CategoryMapper.mapToDto(courseCategory, false));

        try {
            InstructorDto getInstructorById = instructorService.getInstructorById(course.getInstructorId(), correlationId);

            response.put("instructor", getInstructorById);
        } catch (CustomFeignException e) {
            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<ResponseDto> updateCourse(@PathVariable Long id, @Valid @RequestBody UpdateCourseRequest updateCourseRequest) {
        logger.debug("CourseController.updateCourse called");

        Course course = courseService.findById(id);
        Course updatedCourse = courseService.update(course, updateCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(updatedCourse));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui data kursus", response, null));
    }

    @PutMapping("/courses/{courseId}/thumbnail")
    public ResponseEntity<ResponseDto> updateCourseThumbnail(@PathVariable Long courseId,
                                                             @RequestParam("picture") @Valid MultipartFile picture) throws IOException {
        logger.debug("CourseController.updateCourseThumbnail called");

        Course course = courseService.findById(courseId);
        Map<String, Object> response = new HashMap<>();

        try {
            UpdateCourseThumbnailRequest updateCourseThumbnailRequest = new UpdateCourseThumbnailRequest();
            updateCourseThumbnailRequest.setPicture(picture);

            Course updatedCourse = courseService.updateCourseThumbnail(course, updateCourseThumbnailRequest);

            response.put("course", CourseMapper.mapToDto(updatedCourse));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui thumbnail kursus", response, null));
    }

    @PostMapping("/courses/{courseId}/publish")
    public ResponseEntity<ResponseDto> publishCourse(@PathVariable Long courseId) {
        logger.debug("CourseController.publishCourse called");

        Course course = courseService.findById(courseId);
        Course publishedCourse = courseService.publishCourseById(course);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(publishedCourse));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menerbitkan kursus", response, null));
    }

    @PostMapping("/courses/{courseId}/students")
    public ResponseEntity<ResponseDto> getCourseStudents(@PathVariable Long courseId) {

        Map<String, Object> response = new HashMap<>();

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mendapatkan data siswa", response, null));
    }

}
