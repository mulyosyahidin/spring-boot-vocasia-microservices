package com.vocasia.instructor.controller;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.feign.UserDto;
import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.mapper.InstructorMapper;
import com.vocasia.instructor.mapper.UserMapper;
import com.vocasia.instructor.request.UpdateProfileRequest;
import com.vocasia.instructor.service.IAuthenticationService;
import com.vocasia.instructor.service.IInstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Profile Controller", description = "Controller untuk mengelola profile")
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final IInstructorService iInstructorService;
    private final IAuthenticationService iUserService;

    public ProfileController(IInstructorService iInstructorService, IAuthenticationService iUserService) {
        this.iInstructorService = iInstructorService;
        this.iUserService = iUserService;
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

        if (instructor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(false, "Data instruktur tidak ditemukan", null, null));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("instructor", InstructorMapper.mapToDto(instructor));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mendapatkan data instruktur", response, null));
    }

    @Operation(
            summary = "Update Profile Instruktur",
            description = "Update data instruktur"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil memperbarui data instruktur"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Data yang diberikan tidak valid"
            )
    })
    @PutMapping("/profile")
    public ResponseEntity<ResponseDto> updateProfile(@RequestHeader(value = "X-INSTRUCTOR-ID") Long instructorId,
                                                     @RequestParam(value = "profile_picture", required = false) MultipartFile profilePicture,
                                                     @RequestParam("name") String name,
                                                     @RequestParam("email") String email,
                                                     @RequestParam(value = "password", required = false) String password,
                                                     @RequestParam("summary") String summary,
                                                     @RequestParam("phone_number") String phoneNumber) {
        Instructor instructor = iInstructorService.getInstructorById(instructorId);
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();

        updateProfileRequest.setUserId(instructor.getUserId());
        updateProfileRequest.setName(name);
        updateProfileRequest.setEmail(email);
        updateProfileRequest.setPassword(password);
        updateProfileRequest.setSummary(summary);
        updateProfileRequest.setProfilePicture(profilePicture);
        updateProfileRequest.setPhoneNumber(phoneNumber);

        Map<String, Object> response = new HashMap<>();

        try {
            Instructor updatedInstructor = iInstructorService.updateProfile(instructorId, updateProfileRequest);

            UserDto updatedUser = iUserService.updateProfile(updateProfileRequest);

            response.put("instructor", InstructorMapper.mapToDto(updatedInstructor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ResponseDto(false, "Data yang diberikan tidak valid", null, e.getMessage()));
        }

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil memperbarui data instruktur", response, null));
    }

}
