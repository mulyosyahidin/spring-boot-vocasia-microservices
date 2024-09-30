package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.dto.feign.InstructorDto;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.exception.CustomFeignException;
import com.vocasia.authentication.mapper.UserMapper;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.service.IInstructorService;
import com.vocasia.authentication.service.IKeyCloackService;
import com.vocasia.authentication.service.IUserService;
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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Login Controller", description = "Controller untuk login user")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final IUserService iUserService;
    private final IInstructorService iInstructorService;
    private final IKeyCloackService iKeyCloackService;

    public LoginController(IUserService iUserService, IInstructorService iInstructorService, IKeyCloackService iKeyCloackService) {
        this.iUserService = iUserService;
        this.iInstructorService = iInstructorService;
        this.iKeyCloackService = iKeyCloackService;
    }

    @Operation(
            summary = "Login User",
            description = "Login user dengan email dan password"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil login dengan email password"
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
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestHeader("vocasia-correlation-id")
                                             String correlationId, @Valid @RequestBody LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        // login dari database
        User loggedUser = iUserService.loginWithEmailAndPassword(loginRequest);

        if (loggedUser == null) {
            return ResponseEntity.badRequest().body(new ResponseDto(false, "Email atau password salah", null, null));
        }

        // Buat data pengguna untuk respons
        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapToDto(loggedUser));
        response.put("token", iKeyCloackService.getAccessToken(loggedUser.getUsername(), loginRequest.getPassword()));

        if (loggedUser.getRole().equals("instructor")) {
            try {
                InstructorDto getInstructorProfileByUserId = iInstructorService.getInstructorByUserId(loggedUser.getId(), correlationId);

                response.put("instructor", getInstructorProfileByUserId);
            } catch (Exception e) {
                logger.error("Failed to get instructor profile by user id", e);

                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(false, "Internal server error", null, null));
            }
        }

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil login dengan email password", response, null));
    }


}
