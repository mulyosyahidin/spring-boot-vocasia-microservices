package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.mapper.UserMapper;
import com.vocasia.authentication.request.UpdateProfileRequest;
import com.vocasia.authentication.service.IUserService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@Validated
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final IUserService userService;
    ;

    public ProfileController(IUserService iUserService) {
        this.userService = iUserService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> getUserProfile(@PathVariable Long id) {
        logger.info("ProfileController.getUserProfile called");

        User user = userService.findById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapToDto(user));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data pengguna", response, null));
    }

    @PutMapping("/user/{id}/update-user")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Long id,
                                                  @RequestParam(value = "profile_picture", required = false) MultipartFile profilePicture,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("email") String email,
                                                  @RequestParam(value = "password", required = false) String password) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        logger.info("ProfileController.updateUser called");

        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();

        updateProfileRequest.setName(name);
        updateProfileRequest.setEmail(email);
        updateProfileRequest.setPassword(password);
        updateProfileRequest.setProfilePicture(profilePicture);

        User user = userService.findById(id);
        User updatedUser = userService.updateProfile(user, updateProfileRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapToDto(updatedUser));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui data pengguna", response, null));
    }

}
