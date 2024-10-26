package com.vocasia.course.controller.instructor;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;
import com.vocasia.course.service.ICategoryService;
import com.vocasia.course.service.ICourseService;
import com.vocasia.course.service.IInstructorService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
@Validated
public class InstructorCourseController {

    private final Logger logger = LoggerFactory.getLogger(InstructorCourseController.class);

    private final ICategoryService categoryService;
    private final ICourseService courseService;
    private final IInstructorService instructorService;

    public InstructorCourseController(ICategoryService iCategoryService, ICourseService iCourseService, IInstructorService iInstructorService) {
        this.categoryService = iCategoryService;
        this.courseService = iCourseService;
        this.instructorService = iInstructorService;
    }

    @GetMapping("/courses/all")
    public ResponseEntity<ResponseDto> getAllCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId,
                                                     @RequestParam(defaultValue = "1") int page) {
        logger.info("InstructorCourseController.getAllCourses called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Course> courses = courseService.findAllByInstructorId(instructorId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> coursesData = courses.getContent().stream().map(course -> {
            Map<String, Object> courseData = new HashMap<>();

            Category category = categoryService.findById(course.getCategoryId());

            courseData.put("course", CourseMapper.mapToDto(course));
            courseData.put("category", CategoryMapper.mapToDto(category));
            courseData.put("instructor", instructorService.findById(course.getInstructorId(), correlationId));

           return courseData;
        }).toList();

        pagination.put("total_page", courses.getTotalPages());
        pagination.put("per_page", courses.getSize());
        pagination.put("current_page", courses.getNumber() + 1);
        pagination.put("total_items", courses.getTotalElements());

        response.put("data", coursesData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @GetMapping("/courses/draft")
    public ResponseEntity<ResponseDto> getAllDraftCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId,
                                                     @RequestParam(defaultValue = "1") int page) {
        logger.info("InstructorCourseController.getAllDraftCourses called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Course> courses = courseService.findAllByInstructorIdAndStatus(instructorId, "draft", paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> coursesData = courses.getContent().stream().map(course -> {
            Map<String, Object> courseData = new HashMap<>();

            Category category = categoryService.findById(course.getCategoryId());

            courseData.put("course", CourseMapper.mapToDto(course));
            courseData.put("category", CategoryMapper.mapToDto(category));
            courseData.put("instructor", instructorService.findById(course.getInstructorId(), correlationId));

            return courseData;
        }).toList();

        pagination.put("total_page", courses.getTotalPages());
        pagination.put("per_page", courses.getSize());
        pagination.put("current_page", courses.getNumber() + 1);
        pagination.put("total_items", courses.getTotalElements());

        response.put("data", coursesData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @GetMapping("/courses/published")
    public ResponseEntity<ResponseDto> getAllPublishedCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId,
                                                          @RequestParam(defaultValue = "1") int page) {
        logger.info("InstructorCourseController.getAllPublishedCourses called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Course> courses = courseService.findAllByInstructorIdAndStatus(instructorId, "published", paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> coursesData = courses.getContent().stream().map(course -> {
            Map<String, Object> courseData = new HashMap<>();

            Category category = categoryService.findById(course.getCategoryId());

            courseData.put("course", CourseMapper.mapToDto(course));
            courseData.put("category", CategoryMapper.mapToDto(category));
            courseData.put("instructor", instructorService.findById(course.getInstructorId(), correlationId));

            return courseData;
        }).toList();

        pagination.put("total_page", courses.getTotalPages());
        pagination.put("per_page", courses.getSize());
        pagination.put("current_page", courses.getNumber() + 1);
        pagination.put("total_items", courses.getTotalElements());

        response.put("data", coursesData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @PostMapping("/courses")
    public ResponseEntity<ResponseDto> createCourse(@RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                                    @Valid @RequestBody CreateNewCourseRequest createNewCourseRequest) {
        logger.info("InstructorCourseController.createCourse called");

        Course course = courseService.save(instructorId, createNewCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan kursus baru", response, null));
    }

    @PutMapping("/courses/{courseId}/thumbnail")
    public ResponseEntity<ResponseDto> updateCourseThumbnail(@PathVariable Long courseId,
                                                             @RequestParam("picture") @Valid MultipartFile picture) throws IOException {
        logger.info("InstructorCourseController.updateCourseThumbnail called");

        Course course = courseService.findById(courseId);
        Map<String, Object> response = new HashMap<>();

        try {
            UpdateCourseThumbnailRequest updateCourseThumbnailRequest = new UpdateCourseThumbnailRequest();
            updateCourseThumbnailRequest.setPicture(picture);

            Course updatedCourse = courseService.updateCourseThumbnail(course, updateCourseThumbnailRequest);

            response.put("course", CourseMapper.mapToDto(updatedCourse));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui thumbnail kursus", response, null));
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<ResponseDto> getCourseById(@PathVariable Long courseId) {
        logger.info("InstructorCourseController.getCourseById called");

        Course course = courseService.findById(courseId);
        Category courseCategory = categoryService.findById(course.getCategoryId());

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));
        response.put("category", CategoryMapper.mapToDto(courseCategory));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<ResponseDto> updateCourse(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateCourseRequest updateCourseRequest) {
        logger.info("InstructorCourseController.updateCourse called");

        Course course = courseService.findById(id);
        Course updatedCourse = courseService.update(course, updateCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(updatedCourse));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui data kursus", response, null));
    }

    @PostMapping("/courses/{courseId}/publish")
    public ResponseEntity<ResponseDto> publishCourse(@PathVariable Long courseId) {
        logger.info("CourseController.publishCourse called");

        Course course = courseService.findById(courseId);
        Course publishedCourse = courseService.publishCourseById(course);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(publishedCourse));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menerbitkan kursus", response, null));
    }

}
