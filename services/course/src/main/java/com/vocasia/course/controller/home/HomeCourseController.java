package com.vocasia.course.controller.home;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.mapper.ChapterMapper;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.service.ICategoryService;
import com.vocasia.course.service.IChapterService;
import com.vocasia.course.service.ICourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Course Controller", description = "Controller untuk kursus yang ditampilkan di publik")
public class HomeCourseController {

    private final ICourseService courseService;
    private final ICategoryService categoryService;
    private final IChapterService chapterService;

    public HomeCourseController(ICourseService courseService, ICategoryService categoryService, IChapterService chapterService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
        this.chapterService = chapterService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<ResponseDto> categories() {
        List<Category> categories = categoryService.getOnlyParentCategories();

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories.stream().map(CategoryMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menampilkan data kategori", response, null));
    }

    @GetMapping("/public/editor-choices")
    public ResponseEntity<ResponseDto> getEditorsChoices() {
        List<Course> courses = courseService.getEditorsChoices();

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courses.stream().map(CourseMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menampilkan data kursus", response, null));
    }

    @GetMapping("/public/{slug}/{courseId}/overview")
    public ResponseEntity<ResponseDto> getOverview(@PathVariable String slug, @PathVariable Long courseId) {
        Course course = courseService.show(courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menampilkan data kursus", response, null));
    }

    @GetMapping("/public/{slug}/{courseId}/chapters")
    public ResponseEntity<ResponseDto> getChapters(@PathVariable String slug, @PathVariable Long courseId) {
        List<Chapter> chapters = chapterService.index(courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("chapters", chapters.stream().map(ChapterMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menampilkan data kursus", response, null));
    }

}
