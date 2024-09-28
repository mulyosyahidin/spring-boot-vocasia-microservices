package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.mapper.UserMapper;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.service.IKeyCloackService;
import com.vocasia.authentication.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Login Controller", description = "Controller untuk login user")
public class LoginController {

    private final IUserService iUserService;
    private final IKeyCloackService iKeyCloackService;

    public LoginController(IUserService iUserService, IKeyCloackService iKeyCloackService) {
        this.iUserService = iUserService;
        this.iKeyCloackService = iKeyCloackService;
    }

    @Operation(
            summary = "Login User",
            description = "Login user dengan email dan password"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
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
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
       // login dari database
        User loggedUser = iUserService.loginWithEmailAndPassword(loginRequest);

        if (loggedUser == null) {
            return ResponseEntity.badRequest().body(new ResponseDto(false, "Email atau password salah", null, null));
        }

        // Buat data pengguna untuk respons
        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapUserToResponse(loggedUser));
        response.put("token", iKeyCloackService.getAccessToken(loggedUser.getUsername(), loginRequest.getPassword()));

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil login dengan email password", response, null));
    }


}
