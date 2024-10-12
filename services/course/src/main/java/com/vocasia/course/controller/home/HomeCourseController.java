package com.vocasia.course.controller.home;

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
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class HomeCourseController {

    private final Logger logger = LoggerFactory.getLogger(HomeCourseController.class);

    private final ICourseService courseService;
    private final ICategoryService categoryService;
    private final IChapterService chapterService;
    private final IInstructorService instructorService;
    private final ILessonService lessonService;

    public HomeCourseController(ICourseService iCourseService, ICategoryService iCategoryService, IChapterService iChapterService, IInstructorService iInstructorService, ILessonService iLessonService) {
        this.courseService = iCourseService;
        this.categoryService = iCategoryService;
        this.chapterService = iChapterService;
        this.instructorService = iInstructorService;
        this.lessonService = iLessonService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<ResponseDto> categories() {
        logger.info("HomeCourseController.categories called");

        List<Category> categories = categoryService.getOnlyParentCategories();

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories.stream().map(CategoryMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menampilkan data kategori", response, null));
    }

    @GetMapping("/public/editor-choices")
    public ResponseEntity<ResponseDto> getEditorsChoices(@RequestHeader("vocasia-correlation-id") String correlationId) {
        logger.info("HomeCourseController.getEditorsChoices called");

        List<Course> courses = courseService.getEditorsChoices();

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> coursesData = new ArrayList<>();

        for (Course course : courses) {
            Map<String, Object> courseData = new HashMap<>();

            courseData.put("course", CourseMapper.mapToDto(course));
            courseData.put("category", CategoryMapper.mapToDto(course.getCategory(), false));
            courseData.put("instructor", instructorService.findById(course.getInstructorId(), correlationId));

            coursesData.add(courseData);
        }

        response.put("courses", coursesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menampilkan data kursus", response, null));
    }

    @GetMapping("/public/{slug}/{courseId}/overview")
    public ResponseEntity<ResponseDto> getCourseOverview(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                         @PathVariable String slug, @PathVariable Long courseId) {
        logger.info("HomeCourseController.getCourseOverview called");

        Course course = courseService.findById(courseId);
        List<Chapter> chapters = chapterService.findAllByCourseId(course);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));
        response.put("category", CategoryMapper.mapToDto(course.getCategory(), false));

        List<Map<String, Object>> chaptersData = new ArrayList<>();

        for (Chapter chapter : chapters) {
            Map<String, Object> chapterData = new HashMap<>();

            chapterData.put("chapter", ChapterMapper.mapToDto(chapter));
            chapterData.put("lessons", lessonService.findAllByChapterId(chapter.getId()).stream().map(LessonMapper::mapToDto));

            chaptersData.add(chapterData);
        }

        response.put("chapters", chaptersData);

        try {
            InstructorDto getInstructorById = instructorService.findById(course.getInstructorId(), correlationId);

            response.put("instructor", getInstructorById);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.hc.core5.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menampilkan data kursus", response, null));
    }

    @GetMapping("/public/{slug}/{courseId}/chapters")
    public ResponseEntity<ResponseDto> getChapters(@PathVariable String slug, @PathVariable Long courseId) {
        logger.info("HomeCourseController.getChapters called");

        Course course = courseService.findById(courseId);
        List<Chapter> chapters = chapterService.findAllByCourseId(course);

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> chaptersData = new ArrayList<>();

        for (Chapter chapter : chapters) {
            Map<String, Object> chapterData = new HashMap<>();

            chapterData.put("chapter", ChapterMapper.mapToDto(chapter));
            chapterData.put("lessons", lessonService.findAllByChapterId(chapter.getId()).stream().map(LessonMapper::mapToDto));

            chaptersData.add(chapterData);
        }

        response.put("chapters", chaptersData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menampilkan chapter kursus", response, null));
    }

}
