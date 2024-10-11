package com.vocasia.enrollment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.exception.CustomFeignException;
import com.vocasia.enrollment.service.IAuthenticationService;
import com.vocasia.enrollment.service.client.AuthenticationFeignClient;
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

    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final AuthenticationFeignClient authenticationFeignClient;

    @Override
    public UserDto getByUserId(Long userId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> authenticationFeignClientGetUserByIdResponseEntity = authenticationFeignClient.findById(correlationId, userId);
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

}
