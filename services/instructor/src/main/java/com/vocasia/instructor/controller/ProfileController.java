package com.vocasia.instructor.controller;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.mapper.InstructorMapper;
import com.vocasia.instructor.service.IInstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Profile Controller", description = "Controller untuk mengelola profile")
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final IInstructorService iInstructorService;

    public ProfileController(IInstructorService iInstructorService) {
        this.iInstructorService = iInstructorService;
    }

    @Operation(
            summary = "Profile Instruktur",
            description = "Data instruktur"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mendapatkan data instruktur"
    )
    @GetMapping("/profile/{id}")
    public ResponseEntity<ResponseDto> getProfile(@PathVariable Long id) {
        Instructor instructor = iInstructorService.getInstructorById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor", InstructorMapper.mapToDto(instructor));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mendapatkan data instruktur", response, null));
    }

    @Operation(
            summary = "Profile Instruktur Berdasarkan User ID",
            description = "Data instruktur berdasarkan user ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mendapatkan data instruktur berdasarkan user ID"
    )
    @GetMapping("/profile-by-user-id/{userId}")
    public ResponseEntity<ResponseDto> getProfileByUserId(@PathVariable Long userId) {
        Instructor instructor = iInstructorService.getInstructorByUserId(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor", InstructorMapper.mapToDto(instructor));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mendapatkan data instruktur", response, null));
    }

}
