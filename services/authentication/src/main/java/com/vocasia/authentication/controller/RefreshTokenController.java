package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.mapper.AccessTokenMapper;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.request.RefreshTokenRequest;
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
@Tag(name = "Refresh Token Controller", description = "Controller untuk refresh token user")
public class RefreshTokenController {

    private final IKeyCloackService iKeyCloackService;

    public RefreshTokenController(IUserService iUserService, IKeyCloackService iKeyCloackService) {
        this.iKeyCloackService = iKeyCloackService;
    }

    @Operation(
            summary = "Refresh Token",
            description = "Membuat access token baru dengan refresh token"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Berhasil membuat access token baru"
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
    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseDto> refreshAccessToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest)
            throws IOException {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        AccessTokenMapper accessTokenMapper = iKeyCloackService.refreshAccessToken(refreshToken);

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil memperbarui akses token", accessTokenMapper, null));
    }

}
