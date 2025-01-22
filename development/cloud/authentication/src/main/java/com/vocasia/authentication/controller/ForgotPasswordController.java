package com.vocasia.authentication.controller;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.request.CreatePasswordRequest;
import com.vocasia.authentication.request.ForgetPasswordRequest;
import com.vocasia.authentication.service.IForgetPasswordService;
import com.vocasia.authentication.service.IUserService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {

    private final Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);

    private final IForgetPasswordService forgetPasswordService;
    private final IUserService userService;

    public ForgotPasswordController(IForgetPasswordService iForgetPasswordService, IUserService iUserService) {
        this.forgetPasswordService = iForgetPasswordService;
        this.userService = iUserService;
    }

    @PostMapping("/request")
    public ResponseEntity<ResponseDto> requestForgotPassword(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        logger.info("ForgotPasswordController.requestForgotPassword called");

        String email = forgetPasswordRequest.getEmail();
        boolean isUserExists = userService.isUserExists(email);

        if (isUserExists) {
            forgetPasswordService.requestForgotPassword(forgetPasswordRequest);
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil melakukan permintaan reset password", null, null));
    }

    @PostMapping("/create-password")
    public ResponseEntity<ResponseDto> createPassword(@Valid @RequestBody CreatePasswordRequest createPasswordRequest) {
        logger.info("ForgotPasswordController.createPassword called");

        String email = forgetPasswordService.getEmailOfRequest(createPasswordRequest);
        try {
            userService.updatePassword(email, createPasswordRequest.getNewPassword());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil membuat password baru", null, null));
    }

}
