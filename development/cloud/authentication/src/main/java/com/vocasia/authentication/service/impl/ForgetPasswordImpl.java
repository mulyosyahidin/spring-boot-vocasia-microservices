package com.vocasia.authentication.service.impl;

import com.vocasia.authentication.config.AppConfigProperties;
import com.vocasia.authentication.dto.email.EmailSendingDto;
import com.vocasia.authentication.entity.ForgetPassword;
import com.vocasia.authentication.exception.ResourceNotFoundException;
import com.vocasia.authentication.repository.ForgetPasswordRepository;
import com.vocasia.authentication.request.CreatePasswordRequest;
import com.vocasia.authentication.request.ForgetPasswordRequest;
import com.vocasia.authentication.service.IForgetPasswordService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ForgetPasswordImpl implements IForgetPasswordService {

    private final Logger logger = LoggerFactory.getLogger(ForgetPasswordImpl.class);

    private final ForgetPasswordRepository forgetPasswordRepository;
    private final AppConfigProperties appConfigProperties;

    private final StreamBridge streamBridge;

    @Override
    public void requestForgotPassword(ForgetPasswordRequest forgetPasswordRequest) {
        ForgetPassword forgetPassword = new ForgetPassword();

        String token = generateToken();
        String resetUrl = appConfigProperties.getClientAppUrl() + "/auth/forgot-password/reset?token=" + token + "&email=" + forgetPasswordRequest.getEmail();

        forgetPassword.setEmail(forgetPasswordRequest.getEmail());
        forgetPassword.setToken(token);
        forgetPassword.setExpiredAt(LocalDateTime.now().plusMinutes(30));

        forgetPasswordRepository.save(forgetPassword);

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("reset_url", resetUrl);

        EmailSendingDto emailSendingDto = new EmailSendingDto("forget-password", "Lupa Password?", "martinms.za@gmail.com", data);

        logger.debug("Sending email with details: {}", emailSendingDto);
        var result = streamBridge.send("communication-out-0", emailSendingDto);
        logger.debug("Email sent with result: {}", result);
    }

    @Override
    public String getEmailOfRequest(CreatePasswordRequest createPasswordRequest) {
        ForgetPassword forgetPassword = forgetPasswordRepository.findByToken(createPasswordRequest.getToken());

        if (forgetPassword == null) {
            throw new ResourceNotFoundException("Token tidak ditemukan");
        }

        if (forgetPassword.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ResourceNotFoundException("Token sudah kadaluarsa");
        }

        forgetPasswordRepository.delete(forgetPassword);

        return forgetPassword.getEmail();
    }

    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

}
