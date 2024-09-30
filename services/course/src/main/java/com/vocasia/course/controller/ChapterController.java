package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.ChapterMapper;
import com.vocasia.course.request.CreateChapterRequest;
import com.vocasia.course.request.UpdateChapterRequest;
import com.vocasia.course.service.IChapterService;
import com.vocasia.course.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chapters")
@Validated
@Tag(name = "Course Chapter Controller", description = "Controller untuk manajemen chapter kursus")
public class ChapterController {

    private final Logger logger = LoggerFactory.getLogger(CreateCourseController.class);

    private final ICourseService courseService;
    private final IChapterService chapterService;

    public ChapterController(ICourseService courseService, IChapterService chapterService) {
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @Operation(
            summary = "Menambah chapter baru",
            description = "Menambahkan chapter baru ke dalam kursus"
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
    @PostMapping("/{courseId}/add-chapter")
    public ResponseEntity<ResponseDto> createChapter(@PathVariable Long courseId, @Valid @RequestBody CreateChapterRequest createChapterRequest) {
        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.createChapter(course, createChapterRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("chapter", ChapterMapper.mapToDto(chapter));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(true, "Berhasil menambahkan chapter baru", response, null));
    }

    @Operation(
            summary = "Mendapatkan semua chapter",
            description = "Mendapatkan semua chapter dari kursus"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mendapatkan data"
    )
    @GetMapping("/{courseId}/get-all-chapters")
    public ResponseEntity<ResponseDto> getAllCourseChapters(@PathVariable Long courseId) {
        List<Chapter> chapters = chapterService.getChaptersByCourseId(courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("chapters", chapters.stream().map(ChapterMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mendapatkan data chapter", response, null));
    }

    @Operation(
            summary = "Memperbarui data chapter",
            description = "Memperbarui data chapter yang sudah ada"
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
    @PutMapping("/{courseId}/update-chapter/{chapterId}")
    public ResponseEntity<ResponseDto> updateChapter(@PathVariable Long courseId, @PathVariable Long chapterId, @Valid @RequestBody UpdateChapterRequest updateChapterRequest) {
        Chapter chapter = chapterService.updateChapter(chapterId, updateChapterRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("chapter", ChapterMapper.mapToDto(chapter));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(true, "Berhasil memperbarui data chapter", response, null));
    }

    @Operation(
            summary = "Mendapatkan data chapter",
            description = "Mendapatkan data chapter dari kursus"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mendapatkan data"
    )
    @GetMapping("/{courseId}/get-chapter/{chapterId}")
    public ResponseEntity<ResponseDto> getChapterById(@PathVariable Long courseId, @PathVariable Long chapterId) {
        Chapter chapter = chapterService.findById(chapterId);

        Map<String, Object> response = new HashMap<>();
        response.put("chapter", ChapterMapper.mapToDto(chapter));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mendapatkan data chapter", response, null));
    }

    @Operation(
            summary = "Menghapus data chapter",
            description = "Menghapus data chapter dari kursus"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil menghapus data"
    )
    @DeleteMapping("/{courseId}/delete-chapter/{chapterId}")
    public ResponseEntity<ResponseDto> deleteChapterById(@PathVariable Long courseId, @PathVariable Long chapterId) {
        chapterService.deleteChapterById(chapterId);

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menghapus data chapter", null, null));
    }

}
