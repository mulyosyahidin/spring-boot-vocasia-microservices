package com.vocasia.course.controller.admin;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.dto.client.instructor.InstructorDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.exception.CustomFeignException;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.mapper.ChapterMapper;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.mapper.LessonMapper;
import com.vocasia.course.service.*;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminCourseController {

    private final Logger logger = LoggerFactory.getLogger(AdminCourseController.class);

    private final ICategoryService categoryService;
    private final ICourseService courseService;
    private final IInstructorService instructorService;
    private final IChapterService chapterService;
    private final ILessonService lessonService;

    public AdminCourseController(ICategoryService iCategoryService, ICourseService iCourseService, IInstructorService iInstructorService,
                                 IChapterService iChapterService, ILessonService iLessonService) {
        this.categoryService = iCategoryService;
        this.courseService = iCourseService;
        this.instructorService = iInstructorService;
        this.chapterService = iChapterService;
        this.lessonService = iLessonService;
    }

    @GetMapping("/overview")
    public ResponseEntity<ResponseDto> getOverview() {
        logger.info("AdminCourseController.getOverview called");

        Map<String, Object> response = new HashMap<>();
        response.put("total_courses", courseService.count());
        response.put("total_draft_courses", courseService.countByStatus("draft"));
        response.put("total_published_courses", courseService.countByStatus("published"));
        response.put("total_paid_courses", courseService.countByIsFree(false));
        response.put("total_free_courses", courseService.countByIsFree(true));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data ringkasan kursus", response, null));
    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseDto> getAllCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestParam(defaultValue = "1") int page) {
        logger.info("AdminCourseController.getAllCourses called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Course> courses = courseService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> coursesData;

        try {
            coursesData = courses.getContent().stream().map(course -> {
                Map<String, Object> courseData = new HashMap<>();

                InstructorDto instructor = instructorService.findById(course.getInstructorId(), correlationId);

                courseData.put("course", CourseMapper.mapToDto(course));
                courseData.put("instructor", instructor);

                return courseData;
            }).toList();
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

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

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<ResponseDto> getAllCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @PathVariable("courseId") Long courseId) {
        logger.info("AdminCourseController.getCourseById called");

        Map<String, Object> response = new HashMap<>();

        Course course = courseService.findById(courseId);
        Category category = categoryService.findById(course.getCategoryId());

        List<Chapter> chapters = chapterService.findAllByCourseId(course);

        List<Map<String, Object>> chaptersData = new ArrayList<>();

        for (Chapter chapter : chapters) {
            Map<String, Object> chapterData = new HashMap<>();

            chapterData.put("chapter", ChapterMapper.mapToDto(chapter));
            chapterData.put("lessons", lessonService.findAllByChapterId(chapter.getId()).stream().map(LessonMapper::mapToDto));

            chaptersData.add(chapterData);
        }

        response.put("course", CourseMapper.mapToDto(course));
        response.put("category", CategoryMapper.mapToDto(category));
        response.put("chapters", chaptersData);

        try {
            InstructorDto instructor = instructorService.findById(course.getInstructorId(), correlationId);

            response.put("instructor", instructor);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

}
