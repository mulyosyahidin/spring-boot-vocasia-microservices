package com.vocasia.instructor.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.client.authentication.UserDto;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.request.UpdateProfileRequest;
import com.vocasia.instructor.service.IAuthenticationService;
import com.vocasia.instructor.service.client.AuthenticationFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private AuthenticationFeignClient authenticationFeignClient;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public UserDto registerNewUser(RegisterRequest registerRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> authenticationFeignClientRegisterResponseEntity = authenticationFeignClient.register(correlationId, registerRequest);
            ResponseDto responseBody = authenticationFeignClientRegisterResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> user = data != null ? (Map<String, Object>) data.get("user") : null;

            logger.debug("AuthenticationServiceImpl.registerNewUser() $user:: {}", user);

            UserDto userDto = new UserDto();

            if (user != null) {
                userDto.setId(Long.parseLong(user.get("id").toString()));
                userDto.setEmail(user.get("email").toString());
                userDto.setUsername(user.get("username").toString());
                userDto.setName(user.get("name").toString());
                userDto.setRole(user.get("role").toString());
                userDto.setProfilePicture(user.get("profile_picture") != null ? user.get("profile_picture").toString() : null);
                userDto.setProfilePictureUrl(user.get("profile_picture") != null ? user.get("profile_picture_url").toString() : null);
            }

            return userDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public UserDto getByUserId(Long userId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> authenticationFeignClientGetUserByIdResponseEntity = authenticationFeignClient.getUserById(correlationId, userId);
            ResponseDto responseBody = authenticationFeignClientGetUserByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> user = data != null ? (Map<String, Object>) data.get("user") : null;

            logger.debug("AuthenticationServiceImpl.getByUserId() $user:: {}", user);

            UserDto userDto = new UserDto();

            if (user != null) {
                userDto.setId(Long.parseLong(user.get("id").toString()));
                userDto.setEmail(user.get("email").toString());
                userDto.setUsername(user.get("username").toString());
                userDto.setName(user.get("name").toString());
                userDto.setRole(user.get("role").toString());
                userDto.setProfilePicture(user.get("profile_picture") != null ? user.get("profile_picture").toString() : null);
                userDto.setProfilePictureUrl(user.get("profile_picture") != null ? user.get("profile_picture_url").toString() : null);
            }

            return userDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Override
    public UserDto updateProfile(UpdateProfileRequest updateProfileRequest, String correlationId) {
        try {
            ResponseEntity<ResponseDto> authenticationFeignClientUpdateUserResponseEntity = authenticationFeignClient.updateUser(
                    correlationId,
                    updateProfileRequest.getUserId(),
                    updateProfileRequest.getProfilePicture(),
                    updateProfileRequest.getName(),
                    updateProfileRequest.getEmail(),
                    updateProfileRequest.getPassword()
            );
            ResponseDto responseBody = authenticationFeignClientUpdateUserResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> user = data != null ? (Map<String, Object>) data.get("user") : null;

            logger.debug("AuthenticationServiceImpl.updateProfile() $user:: {}", user);

            UserDto userDto = new UserDto();

            if (user != null) {
                userDto.setId(Long.parseLong(user.get("id").toString()));
                userDto.setEmail(user.get("email").toString());
                userDto.setUsername(user.get("username").toString());
                userDto.setName(user.get("name").toString());
                userDto.setRole(user.get("role").toString());
                userDto.setProfilePicture(user.get("profile_picture") != null ? user.get("profile_picture").toString() : null);
                userDto.setProfilePictureUrl(user.get("profile_picture") != null ? user.get("profile_picture_url").toString() : null);
            }

            return userDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
