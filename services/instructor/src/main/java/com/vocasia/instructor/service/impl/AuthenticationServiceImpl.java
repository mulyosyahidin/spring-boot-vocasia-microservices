package com.vocasia.instructor.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.instructor.dto.UserDto;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.service.IAuthenticationService;
import com.vocasia.instructor.service.client.AuthenticationFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private AuthenticationFeignClient authenticationFeignClient;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public UserDto registerNewUser(RegisterRequest registerRequest, String correlationId) {
        try {
            ResponseEntity<Map<String, Object>> registerNewUserResponseEntity = authenticationFeignClient.register(correlationId, registerRequest);

            Map<String, Object> responseBody = registerNewUserResponseEntity.getBody();
            logger.info("Response Body: {}", responseBody);

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    Map<String, Object> user = (Map<String, Object>) data.get("user");
                    logger.info("User Data: {}", user);

                    UserDto userDto = new UserDto();

                    userDto.setId(Long.parseLong(user.get("id").toString()));
                    userDto.setUid(user.get("uid").toString());
                    userDto.setEmail(user.get("email").toString());
                    userDto.setUsername(user.get("username").toString());
                    userDto.setName(user.get("name").toString());
                    userDto.setRole(user.get("role").toString());
                    userDto.setProfilePicture(user.get("profile_picture") != null ? user.get("profile_picture").toString() : null);
                    userDto.setCreatedAt(LocalDateTime.parse(user.get("created_at").toString()));
                    userDto.setUpdatedAt(LocalDateTime.parse(user.get("updated_at").toString()));

                    return userDto;
                }
            } else {
                logger.warn("Registration failed: {}", responseBody != null ? responseBody.get("message") : "No response body");
            }

            return null;
        } catch (FeignException e) {
            try {
                Map<String, Object> responseBody = new ObjectMapper().readValue(e.contentUTF8(), new TypeReference<Map<String, Object>>() {});
                Object errors = responseBody.get("errors");

                throw new CustomFeignException("Validation error", errors);
            } catch (IOException ioException) {
                throw new RuntimeException("Failed to parse error response", ioException);
            }
        }
    }

}
