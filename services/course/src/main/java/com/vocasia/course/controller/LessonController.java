package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.mapper.LessonMapper;
import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.request.lesson.UpdateLessonRequest;
import com.vocasia.course.service.IChapterService;
import com.vocasia.course.service.ICourseService;
import com.vocasia.course.service.ILessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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

    private final IChapterService chapterService;
    private final ILessonService lessonService;

    public LessonController(IChapterService chapterService, ILessonService lessonService) {
        this.chapterService = chapterService;
        this.lessonService = lessonService;
    }

    @Operation(
            summary = "Menampilkan data pelajaran",
            description = "Menampilkan data pelajaran berdasarkan chapter"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mengambil data"
    )
    @GetMapping("/{courseId}/chapters/{chapterId}/lessons")
    public ResponseEntity<ResponseDto> index(@PathVariable Long courseId, @PathVariable Long chapterId) {
        List<Lesson> lessons = lessonService.index(chapterId);

        Map<String, Object> response = new HashMap<>();
        response.put("lessons", lessons.stream().map(LessonMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mengambil data pelajaran", response, null));
    }

    @Operation(
            summary = "Menambah lesson baru",
            description = "Menambahkan lesson baru ke dalam chapter"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Berhasil menambahkan data"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Data yang diberikan tidak valid"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @PostMapping("/{courseId}/chapters/{chapterId}/lessons")
    public ResponseEntity<ResponseDto> store(@PathVariable Long courseId, @PathVariable Long chapterId, @Valid @RequestBody StoreLessonRequest storeLessonRequest) {
        Chapter chapter = chapterService.show(chapterId);
        Lesson lesson = lessonService.store(chapter, storeLessonRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", LessonMapper.mapToDto(lesson));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menambahkan pelajaran", response, null));
    }

    @Operation(
            summary = "Menampilkan data pelajaran",
            description = "Menampilkan data pelajaran berdasarkan chapter"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mengambil data"
    )
    @GetMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> show(@PathVariable Long courseId, @PathVariable Long chapterId, @PathVariable Long lessonId) {
        Lesson lesson = lessonService.show(lessonId);

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", LessonMapper.mapToDto(lesson));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menampilkan data pelajaran", response, null));
    }

    @Operation(
            summary = "Memperbarui data pelajaran",
            description = "Memperbarui data pelajaran berdasarkan chapter"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil memperbarui data"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Data yang diberikan tidak valid"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @PutMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long courseId, @PathVariable Long chapterId, @PathVariable Long lessonId, @Valid @RequestBody UpdateLessonRequest updateLessonRequest) {
        Lesson lesson = lessonService.show(lessonId);
        Lesson updatedLesson = lessonService.update(lesson, updateLessonRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", LessonMapper.mapToDto(updatedLesson));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil memperbarui data pelajaran", response, null));
    }

    @Operation(
            summary = "Menghapus data pelajaran",
            description = "Menghapus data pelajaran berdasarkan chapter"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil menghapus data"
    )
    @DeleteMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> destroy(@PathVariable Long courseId, @PathVariable Long chapterId, @PathVariable Long lessonId) {
        Lesson lesson = lessonService.show(lessonId);
        lessonService.delete(lesson);

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menghapus data pelajaran", null, null));
    }

}
