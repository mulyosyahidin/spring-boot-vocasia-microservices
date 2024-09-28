package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.mapper.UserMapper;
import com.vocasia.authentication.request.RegisterRequest;
import com.vocasia.authentication.service.IKeyCloackService;
import com.vocasia.authentication.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Register Controller", description = "Controller untuk registrasi user")
public class RegisterController {

    private final IUserService iUserService;
    private final IKeyCloackService iKeyCloackService;

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    public RegisterController(IUserService iUserService, IKeyCloackService iKeyCloackService) {
        this.iUserService = iUserService;
        this.iKeyCloackService = iKeyCloackService;
    }

    @Operation(
            summary = "Register User",
            description = "Mendaftarkan user baru dengan data yang diberikan."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User berhasil didaftarkan"
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
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody RegisterRequest registerRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        // Registrasi pengguna di Keycloak
        String registeredKeycloackId = iKeyCloackService.registerNewUser(
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getName(),
                registerRequest.getRole()
        );

        // Registrasi pengguna di database lokal
        User registeredUser = iUserService.registerNewUser(registeredKeycloackId, registerRequest);

        // Buat data pengguna untuk respons
        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapUserToResponse(registeredUser));
        response.put("token", iKeyCloackService.getAccessToken(registerRequest.getUsername(), registerRequest.getPassword()));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mendaftarkan user baru", response, null));
    }

}
