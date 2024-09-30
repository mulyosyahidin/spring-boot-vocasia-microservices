package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;
import com.vocasia.course.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/create-courses")
@Validated
@Tag(name = "Create Course Controller", description = "Controller untuk menambahkan kursus baru oleh instruktur")
public class CreateCourseController {

    private final Logger logger = LoggerFactory.getLogger(CreateCourseController.class);

    private final ICourseService courseService;

    public CreateCourseController(ICourseService courseService) {
        this.courseService = courseService;
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
    @PostMapping("/new")
    public ResponseEntity<ResponseDto> createNew(@Valid @RequestBody CreateNewCourseRequest createNewCourseRequest) {
        Course createCourse = courseService.createNewCourse(createNewCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(createCourse));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(true, "Berhasil menambahkan kursus baru", response, null));
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
    @PutMapping("/{courseId}/update-thumbnail")
    public ResponseEntity<ResponseDto> updateThumbnail(
            @PathVariable Long courseId,
            @RequestParam("picture") @Valid MultipartFile picture) throws IOException {

        Course getCourse = courseService.findById(courseId);
        Map<String, Object> response = new HashMap<>();

        try {
            UpdateCourseThumbnailRequest updateCourseThumbnailRequest = new UpdateCourseThumbnailRequest();
            updateCourseThumbnailRequest.setPicture(picture);
            Course updatedCourse = courseService.updateThumbnail(getCourse, updateCourseThumbnailRequest);

            response.put("course", CourseMapper.mapToDto(updatedCourse));
        } catch (IOException e) {
            logger.error("Gagal mengupdate thumbnail", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(false, "Gagal mengupdate thumbnail", null, e.getMessage()));
        }


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Berhasil mengupdate thumbnail", response, null));
    }

}
