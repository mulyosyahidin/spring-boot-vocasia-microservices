package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.mapper.UserMapper;
import com.vocasia.authentication.request.RegisterRequest;
import com.vocasia.authentication.service.IKeyCloackService;
import com.vocasia.authentication.service.IUserService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RegisterController {

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final IUserService userService;
    private final IKeyCloackService keyCloackService;

    public RegisterController(IUserService iUserService, IKeyCloackService iKeyCloackService) {
        this.userService = iUserService;
        this.keyCloackService = iKeyCloackService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                @Valid @RequestBody RegisterRequest registerRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        logger.info("RegisterController.register called");

        String registeredKeycloakId = keyCloackService.registerNewUser(
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getName(),
                registerRequest.getRole()
        );

        User registeredUser = userService.registerNewUser(registeredKeycloakId, registerRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapToDto(registeredUser));
        response.put("token", keyCloackService.getAccessToken(registerRequest.getUsername(), registerRequest.getPassword()));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil mendaftarkan user baru", response, null));
    }

}
