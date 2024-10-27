package com.vocasia.course.controller.student;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.mapper.ChapterMapper;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.mapper.LessonMapper;
import com.vocasia.course.service.*;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentCourseController {

    private final Logger logger = LoggerFactory.getLogger(StudentCourseController.class);

    private final ICategoryService categoryService;
    private final ICourseService courseService;
    private final IChapterService chapterService;
    private final ILessonService lessonService;

    public StudentCourseController(ICategoryService iCategoryService, ICourseService iCourseService, IChapterService iChapterService, ILessonService iLessonService) {
        this.categoryService = iCategoryService;
        this.courseService = iCourseService;
        this.chapterService = iChapterService;
        this.lessonService = iLessonService;
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

    @GetMapping("/{slug}/{courseId}/contents")
    public ResponseEntity<ResponseDto> getContents(@PathVariable String slug, @PathVariable Long courseId) {
        logger.info("HomeCourseController.getContents called");

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
                .status(org.apache.http.HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menampilkan chapter kursus", response, null));
    }

}
