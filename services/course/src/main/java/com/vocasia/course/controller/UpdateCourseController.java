package com.vocasia.course.controller;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Course;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
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
import java.util.Map;

@RestController
@RequestMapping("/api/update-course")
@Validated
@Tag(name = "Update Course Controller", description = "Controller untuk mengubah data kursus")
public class UpdateCourseController {

    private final Logger logger = LoggerFactory.getLogger(CreateCourseController.class);

    private final ICourseService courseService;

    public UpdateCourseController(ICourseService courseService) {
        this.courseService = courseService;
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
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateCourseRequest updateCourseRequest) {
        Course updatedCourse = courseService.updateCourse(id, updateCourseRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("course", CourseMapper.mapToDto(updatedCourse));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil memperbarui data kursus", response, null));
    }
}
