package com.vocasia.finance.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.dto.client.authentication.UserDto;
import com.vocasia.finance.exception.CustomFeignException;
import com.vocasia.finance.service.IAuthenticationService;
import com.vocasia.finance.service.client.AuthenticationFeignClient;
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
    public UserDto findUserById(Long userId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> authenticationFeignClientFindUserByIdResponseEntity = authenticationFeignClient.findUserById(correlationId, userId);
            ResponseDto responseBody = authenticationFeignClientFindUserByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> user = data != null ? (Map<String, Object>) data.get("user") : null;

            logger.debug("AuthenticationServiceImpl.findUserById() $user:: {}", user);

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
