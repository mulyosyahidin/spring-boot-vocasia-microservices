package com.vocasia.enrollment.service.impl;

import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.entity.client.User;
import com.vocasia.enrollment.mapper.client.UserMapper;
import com.vocasia.enrollment.service.IAuthenticationService;
import com.vocasia.enrollment.service.client.AuthenticationFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final AuthenticationFeignClient authenticationFeignClient;

    @Override
    public UserDto getUserById(Long userId) {
        try {
            ResponseEntity<Map<String, Object>> authenticationFeignClientUserById = authenticationFeignClient.getUserById(userId);
            Map<String, Object> responseBody = authenticationFeignClientUserById.getBody();

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    Map<String, Object> user = (Map<String, Object>) data.get("user");

                    logger.info("User data impl: {}", user);

                    if (user != null) {
                        UserDto userDto = new UserDto();

                        userDto.setId(Long.valueOf(user.get("id").toString()));
                        userDto.setUid(user.get("uid").toString());
                        userDto.setEmail(user.get("email").toString());
                        userDto.setUsername(user.get("username").toString());
                        userDto.setName(user.get("name").toString());
                        userDto.setRole(user.get("role").toString());
                        userDto.setProfilePicture(user.get("profile_picture") != null ? user.get("profile_picture").toString() : null);
                        userDto.setProfilePictureUrl(user.get("profile_picture_url") != null ? user.get("profile_picture_url").toString() : null);
                        userDto.setCreatedAt(LocalDateTime.parse(user.get("created_at").toString()));
                        userDto.setUpdatedAt(LocalDateTime.parse(user.get("updated_at").toString()));

                        return userDto;
                    }
                    else {
                        logger.warn("Failed to get user by id [no user data]: {}", userId);
                    }
                }
                else {
                    logger.warn("Failed to get user by id [no data]: {}", userId);
                }
            }
            else {
                logger.warn("Failed to get user by id [no response body]: {}", userId);
            }

            return null;
        } catch (FeignException e) {
            logger.warn("Failed to get user by id: {}", userId);

            throw new RuntimeException("Failed to parse error response", e);
        }
    }

}
