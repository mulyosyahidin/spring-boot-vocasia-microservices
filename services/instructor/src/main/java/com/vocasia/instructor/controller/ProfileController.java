package com.vocasia.instructor.controller;

import com.vocasia.authentication.exception.ResourceNotFoundException;
import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.client.authentication.UserDto;
import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.mapper.InstructorMapper;
import com.vocasia.instructor.request.UpdateProfileRequest;
import com.vocasia.instructor.service.IAuthenticationService;
import com.vocasia.instructor.service.IInstructorService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final IInstructorService instructorService;
    private final IAuthenticationService authenticationService;

    public ProfileController(IInstructorService iInstructorService, IAuthenticationService iAuthenticationService) {
        this.instructorService = iInstructorService;
        this.authenticationService = iAuthenticationService;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ResponseDto> getProfile(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                  @PathVariable Long id) {
        logger.info("ProfileController.getProfile called");

        Instructor instructor = instructorService.getInstructorById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor", InstructorMapper.mapToDto(instructor));

        try {
            UserDto userDto = authenticationService.findUserById(instructor.getUserId(), correlationId);

            response.put("user", userDto);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Internal server error", null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data instruktur", response, null));
    }

    @GetMapping("/profile-by-user-id/{userId}")
    public ResponseEntity<ResponseDto> getProfileByUserId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @PathVariable Long userId) {
        logger.info("ProfileController.getProfileByUserId called");

        Instructor instructor = instructorService.getInstructorByUserId(userId);

        if (instructor == null) {
            throw new ResourceNotFoundException("Data tidak ditemukan");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("instructor", InstructorMapper.mapToDto(instructor));

        try {
            UserDto userDto = authenticationService.findUserById(instructor.getUserId(), correlationId);

            response.put("user", userDto);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Internal server error", null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data instruktur", response, null));
    }

    @PutMapping("/profile")
    public ResponseEntity<ResponseDto> updateProfile(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestHeader(value = "X-INSTRUCTOR-ID") Long instructorId,
                                                     @RequestParam(value = "profile_picture", required = false) MultipartFile profilePicture,
                                                     @RequestParam("name") String name,
                                                     @RequestParam("email") String email,
                                                     @RequestParam(value = "password", required = false) String password,
                                                     @RequestParam("summary") String summary,
                                                     @RequestParam("phone_number") String phoneNumber) {
        logger.info("ProfileController.updateProfile called");

        Instructor instructor = instructorService.getInstructorById(instructorId);
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();

        updateProfileRequest.setUserId(instructor.getUserId());
        updateProfileRequest.setName(name);
        updateProfileRequest.setEmail(email);
        updateProfileRequest.setPassword(password);
        updateProfileRequest.setSummary(summary);
        updateProfileRequest.setProfilePicture(profilePicture);
        updateProfileRequest.setPhoneNumber(phoneNumber);

        Instructor updatedInstructor = instructorService.updateProfile(instructorId, updateProfileRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor", InstructorMapper.mapToDto(updatedInstructor));

        try {
            UserDto updatedUser = authenticationService.updateUser(updateProfileRequest, correlationId);
            response.put("user", updatedUser);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui data instruktur", response, null));
    }

}
