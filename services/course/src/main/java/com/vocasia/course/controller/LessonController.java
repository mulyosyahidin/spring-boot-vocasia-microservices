package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.mapper.LessonMapper;
import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.request.lesson.UpdateLessonRequest;
import com.vocasia.course.service.IChapterService;
import com.vocasia.course.service.ICourseService;
import com.vocasia.course.service.ILessonService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LessonController {

    private final Logger logger = LoggerFactory.getLogger(LessonController.class);

    private final ICourseService courseService;
    private final IChapterService chapterService;
    private final ILessonService lessonService;

    public LessonController(ICourseService iCourseService, IChapterService iChapterService, ILessonService iLessonService) {
        this.courseService = iCourseService;
        this.chapterService = iChapterService;
        this.lessonService = iLessonService;
    }

    @GetMapping("/{courseId}/chapters/{chapterId}/lessons")
    public ResponseEntity<ResponseDto> getAllLessonByChapterId(@PathVariable Long courseId, @PathVariable Long chapterId) {
        logger.debug("LessonController.getAllLessonByChapterId called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        List<Lesson> lessons = lessonService.findAllByChapterId(chapterId);

        Map<String, Object> response = new HashMap<>();
        response.put("lessons", lessons.stream().map(LessonMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mengambil data pelajaran", response, null));
    }

    @PostMapping("/{courseId}/chapters/{chapterId}/lessons")
    public ResponseEntity<ResponseDto> createLesson(@PathVariable Long courseId, @PathVariable Long chapterId, @Valid @RequestBody StoreLessonRequest storeLessonRequest) {
        logger.debug("LessonController.createLesson called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        Lesson lesson = lessonService.save(chapter, storeLessonRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", LessonMapper.mapToDto(lesson));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambah data pelajaran", response, null));
    }

    @GetMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> getLessonById(@PathVariable Long courseId, @PathVariable Long chapterId, @PathVariable Long lessonId) {
        logger.debug("LessonController.getLessonById called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        Lesson lesson = lessonService.findById(lessonId);

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", LessonMapper.mapToDto(lesson));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mengambil data pelajaran", response, null));
    }

    @PutMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> updateLesson(@PathVariable Long courseId, @PathVariable Long chapterId, @PathVariable Long lessonId, @Valid @RequestBody UpdateLessonRequest updateLessonRequest) {
        logger.debug("LessonController.updateLesson called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        Lesson lesson = lessonService.findByChapterIdAndId(chapterId, lessonId);

        Lesson updatedLesson = lessonService.update(lesson, updateLessonRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", LessonMapper.mapToDto(updatedLesson));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui data pelajaran", response, null));
    }

    @DeleteMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> deleteLesson(@PathVariable Long courseId, @PathVariable Long chapterId, @PathVariable Long lessonId) {
        logger.debug("LessonController.deleteLesson called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        Lesson lesson = lessonService.findByChapterIdAndId(chapterId, lessonId);

        lessonService.deleteById(lesson);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menghapus data pelajaran", null, null));
    }

}
