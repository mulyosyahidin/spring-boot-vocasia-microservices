package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.mapper.AccessTokenMapper;
import com.vocasia.authentication.request.RefreshTokenRequest;
import com.vocasia.authentication.service.IKeyCloackService;
import com.vocasia.authentication.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Validated
public class RefreshTokenController {

    private final IKeyCloackService iKeyCloackService;

    public RefreshTokenController(IUserService iUserService, IKeyCloackService iKeyCloackService) {
        this.iKeyCloackService = iKeyCloackService;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseDto> refreshAccessToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest)
            throws IOException {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        AccessTokenMapper accessTokenMapper = iKeyCloackService.refreshAccessToken(refreshToken);

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil memperbarui akses token", accessTokenMapper, null));
    }

}
