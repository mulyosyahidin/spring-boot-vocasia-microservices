package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.mapper.ChapterMapper;
import com.vocasia.course.mapper.LessonMapper;
import com.vocasia.course.request.CreateChapterRequest;
import com.vocasia.course.request.UpdateChapterRequest;
import com.vocasia.course.service.IChapterService;
import com.vocasia.course.service.ICourseService;
import com.vocasia.course.service.ILessonService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
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
public class ChapterController {

    private final Logger logger = LoggerFactory.getLogger(ChapterController.class);

    private final ICourseService courseService;
    private final IChapterService chapterService;
    private final ILessonService lessonService;

    public ChapterController(ICourseService iCourseService, IChapterService iChapterService, ILessonService iLessonService) {
        this.courseService = iCourseService;
        this.chapterService = iChapterService;
        this.lessonService = iLessonService;
    }

    @GetMapping("/{courseId}/chapters")
    public ResponseEntity<ResponseDto> getAllCourseChapters(@PathVariable Long courseId) {
        logger.debug("ChapterController.getAllCourseChapters called");

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
                .body(new ResponseDto(true, "Berhasil mendapatkan data chapter", response, null));
    }

    @PostMapping("/{courseId}/chapters")
    public ResponseEntity<ResponseDto> createChapter(@PathVariable Long courseId, @Valid @RequestBody CreateChapterRequest createChapterRequest) {
        logger.debug("ChapterController.createChapter called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.save(course, createChapterRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("chapter", ChapterMapper.mapToDto(chapter));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan chapter baru", response, null));
    }

    @GetMapping("/{courseId}/chapters/{chapterId}")
    public ResponseEntity<ResponseDto> getChapterById(@PathVariable Long courseId, @PathVariable Long chapterId) {
        logger.debug("ChapterController.getChapterById called");

        Chapter chapter = chapterService.findById(chapterId);
        List<Lesson> chapterLessons = lessonService.findAllByChapterId(chapterId);

        Map<String, Object> response = new HashMap<>();
        response.put("chapter", ChapterMapper.mapToDto(chapter));
        response.put("lessons", chapterLessons.stream().map(LessonMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data chapter", response, null));
    }

    @PutMapping("/{courseId}/chapters/{chapterId}")
    public ResponseEntity<ResponseDto> updateChapter(@PathVariable Long courseId, @PathVariable Long chapterId, @Valid @RequestBody UpdateChapterRequest updateChapterRequest) {
        logger.debug("ChapterController.updateChapter called");

        Chapter chapter = chapterService.findById(chapterId);
        Chapter updatedChapter = chapterService.update(chapter, updateChapterRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("chapter", ChapterMapper.mapToDto(updatedChapter));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui data chapter", response, null));
    }

    @DeleteMapping("/{courseId}/chapters/{chapterId}")
    public ResponseEntity<ResponseDto> deleteChapter(@PathVariable Long courseId, @PathVariable Long chapterId) {
        logger.debug("ChapterController.deleteChapter called");

        Chapter chapter = chapterService.findById(chapterId);
        chapterService.deleteById(chapter);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menghapus data chapter", null, null));
    }

}
