package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;
import com.vocasia.course.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Course Controller", description = "Controller untuk manajemen kursus kursus")
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(
            summary = "Mendapatkan semua kursus",
            description = "Mengambil semua kursus yang dimiliki oleh instruktur atau semua instruktur"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil mendapatkan data"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @GetMapping("/courses/all")
    public ResponseEntity<ResponseDto> indexAll(@RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId) {
        List<Course> courses = List.of();

        if (instructorId != null) {
            courses = courseService.getAllCoursesByInstructorId(instructorId);
        } else {
            courses = courseService.getAllCourses();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courses.stream().map(CourseMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Data semua kursus berhasil didapatkan", response, null));
    }

    @Operation(
            summary = "Mendapatkan semua kursus",
            description = "Mengambil semua kursus yang dimiliki oleh instruktur atau semua instruktur"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil mendapatkan data"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @GetMapping("/courses/published")
    public ResponseEntity<ResponseDto> indexPublished(@RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId) {
        List<Course> courses = List.of();

        if (instructorId != null) {
            courses = courseService.getPublishedCoursesByInstructorId(instructorId);
        } else {
            courses = courseService.getPublishedCourses();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courses.stream().map(CourseMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Data semua kursus berhasil didapatkan", response, null));
    }

    @Operation(
            summary = "Mendapatkan draft kursus",
            description = "Mengambil draft kursus yang dimiliki oleh instruktur atau semua instruktur"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil mendapatkan data"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @GetMapping("/courses/draft")
    public ResponseEntity<ResponseDto> indexDraft(@RequestHeader(value = "X-INSTRUCTOR-ID", required = false) Long instructorId) {
        List<Course> courses = List.of();

        if (instructorId != null) {
            courses = courseService.getDraftCoursesByInstructorId(instructorId);
        } else {
            courses = courseService.getDraftCourses();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courses.stream().map(CourseMapper::mapToDto));

        return ResponseEntity.ok(new ResponseDto(true, "Data draft kursus berhasil didapatkan", response, null));
    }

    @Operation(
            summary = "Membuat kursus baru",
            description = "Menambahkan kursus baru dengan data dasar seperti nama, deskripsi, dan sebagainya"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
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
    @PostMapping("/courses")
    public ResponseEntity<ResponseDto> store(@Valid @RequestBody CreateNewCourseRequest createNewCourseRequest) {
        Course course = courseService.store(createNewCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(true, "Berhasil menambahkan kursus baru", response, null));
    }

    @Operation(
            summary = "Mendapatkan data kursus berdasarkan ID",
            description = "Mengambil data kursus berdasarkan ID yang diberikan"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil mendapatkan data"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<ResponseDto> show(@PathVariable Long courseId) {
        Course course = courseService.show(courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(course));

        return ResponseEntity.ok(new ResponseDto(true, "Data kursus berhasil didapatkan", response, null));
    }

    @Operation(
            summary = "Mengubah data kursus",
            description = "Mengubah data kursus seperti nama, deskripsi, dan sebagainya"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil mengubah data"
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
    @PutMapping("/courses/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateCourseRequest updateCourseRequest) {
        Course updatedCourse = courseService.updateCourse(id, updateCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(updatedCourse));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil memperbarui data kursus", response, null));
    }

    @Operation(
            summary = "Update thumbnail",
            description = "Mengupdate thumbnail dari kursus yang sudah ada"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil mengupdate thumbnail"
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
    @PutMapping("/courses/{courseId}/thumbnail")
    public ResponseEntity<ResponseDto> updateThumbnail(
            @PathVariable Long courseId,
            @RequestParam("picture") @Valid MultipartFile picture) throws IOException {

        Course getCourse = courseService.show(courseId);
        Map<String, Object> response = new HashMap<>();

        try {
            UpdateCourseThumbnailRequest updateCourseThumbnailRequest = new UpdateCourseThumbnailRequest();
            updateCourseThumbnailRequest.setPicture(picture);

            Course updatedCourse = courseService.updateThumbnail(getCourse, updateCourseThumbnailRequest);

            response.put("course", CourseMapper.mapToDto(updatedCourse));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(false, "Gagal mengupdate thumbnail", null, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Berhasil mengupdate thumbnail", response, null));
    }

    @Operation(
            summary = "Terbitkan kursus",
            description = "Menerbitkan kursus yang sudah dibuat"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil menerbitkan kursus"
    )
    @PostMapping("/courses/{courseId}/publish")
    public ResponseEntity<ResponseDto> publish(@PathVariable Long courseId) {
        Course course = courseService.show(courseId);
        Course publishedCourse = courseService.publish(course);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(publishedCourse));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menerbitkan kursus", response, null));
    }

    @Operation(
            summary = "Mendapatkan data siswa",
            description = "Mengambil data siswa yang terdaftar pada kursus"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mendapatkan data siswa"
    )
    @PostMapping("/courses/{courseId}/students")
    public ResponseEntity<ResponseDto> getStudents(@PathVariable Long courseId) {
        Course course = courseService.show(courseId);
        Course publishedCourse = courseService.publish(course);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(publishedCourse));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil menerbitkan kursus", response, null));
    }

}
