package com.vocasia.course.controller.admin;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.service.ICategoryService;
import com.vocasia.course.service.ICourseService;
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
@RequestMapping("/api/admin")
public class AdminInstructorCourseController {

    private final Logger logger = LoggerFactory.getLogger(AdminInstructorCourseController.class);

    private final ICourseService courseService;
    private final ICategoryService categoryService;

    public AdminInstructorCourseController(ICourseService courseService, ICategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    @GetMapping("/instructor-courses")
    public ResponseEntity<ResponseDto> getAllInstructorCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                               @RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                                               @RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "all") String status) {
        logger.info("InstructorCourseController.getAllInstructorCourses called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Course> courses = switch (status) {
            case "published" -> courseService.findAllPublishedByInstructorId(instructorId, paging);
            case "draft" -> courseService.findAllDraftByInstructorId(instructorId, paging);
            default -> courseService.findAllByInstructorId(instructorId, paging);
        };

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> coursesData = courses.getContent().stream().map(course -> {
            Map<String, Object> courseData = new HashMap<>();

            Category category = categoryService.findById(course.getCategoryId());

            courseData.put("course", CourseMapper.mapToDto(course));
            courseData.put("category", CategoryMapper.mapToDto(category));

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
                .body(new ResponseDto(true, "Berhasil menampilkan data kursus instruktur", response, null));
    }

}
