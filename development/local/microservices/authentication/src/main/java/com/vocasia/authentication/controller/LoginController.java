package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.dto.client.instructor.InstructorDto;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.exception.CustomFeignException;
import com.vocasia.authentication.mapper.UserMapper;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.service.IInstructorService;
import com.vocasia.authentication.service.IKeyCloackService;
import com.vocasia.authentication.service.IUserService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
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
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final IUserService userService;
    private final IInstructorService instructorService;
    private final IKeyCloackService keyCloackService;

    public LoginController(IUserService iUserService, IInstructorService IInstructorService, IKeyCloackService iKeyCloackService) {
        this.userService = iUserService;
        this.instructorService = IInstructorService;
        this.keyCloackService = iKeyCloackService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestHeader("vocasia-correlation-id") String correlationId,
                                             @Valid @RequestBody LoginRequest loginRequest)
            throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        logger.info("LoginController.login called");

        User loggedUser = userService.loginWithEmailAndPassword(loginRequest);

        if (loggedUser == null) {
            return ResponseEntity
                    .status(HttpStatus.SC_BAD_REQUEST)
                    .body(new ResponseDto(false, "Email atau password salah", null, null));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapToDto(loggedUser));

        try {
            if (loggedUser.getUid() == null || !keyCloackService.isUserExists(loggedUser.getUid())) {
                logger.info("User not found in Keycloak, registering user in Keycloak...");

                String keycloakId = keyCloackService.registerNewUser(
                        loggedUser.getEmail(),
                        loggedUser.getUsername(),
                        loginRequest.getPassword(),
                        loggedUser.getName(),
                        loggedUser.getRole()
                );

                loggedUser = userService.updateUid(loggedUser.getId(), keycloakId);

                logger.info("User registered in Keycloak with ID: " + keycloakId);
            }

            response.put("token", keyCloackService.getAccessToken(loggedUser.getUsername(), loginRequest.getPassword()));
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if ("instructor".equals(loggedUser.getRole())) {
            try {
                InstructorDto getInstructorProfileByUserId = instructorService.findByUserId(loggedUser.getId(), correlationId);
                response.put("instructor", getInstructorProfileByUserId);
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
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil login dengan email password", response, null));
    }

}
