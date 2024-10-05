package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.mapper.UserMapper;
import com.vocasia.authentication.request.UpdateProfileRequest;
import com.vocasia.authentication.service.IUserService;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Profile Controller", description = "Controller untuk mengelola data profile pengguna")
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final IUserService iUserService;

    public ProfileController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Operation(
            summary = "Profil pengguna",
            description = "Mengambil data profil pengguna berdasarkan ID pengguna"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Berhasil mengambil data profil pengguna"
    )
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> getUserProfile(@PathVariable Long id) {
        User user = iUserService.findById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapToDto(user));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mengambil data pengguna", response, null));
    }

    @PutMapping("/user/{id}/update-user")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Long id,
                                                  @RequestParam(value = "profile_picture", required = false) MultipartFile profilePicture,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("email") String email,
                                                  @RequestParam(value = "password", required = false) String password) {
        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();

        updateProfileRequest.setName(name);
        updateProfileRequest.setEmail(email);
        updateProfileRequest.setPassword(password);
        updateProfileRequest.setProfilePicture(profilePicture);

        Map<String, Object> response = new HashMap<>();

        try {
            User updatedUser = iUserService.updateProfile(id, updateProfileRequest);

            response.put("user", UserMapper.mapToDto(updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ResponseDto(false, "Data yang diberikan tidak valid", null, e.getMessage()));
        }

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil memperbarui data pengguna", response, null));
    }
}
