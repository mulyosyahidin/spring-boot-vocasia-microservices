package com.vocasia.instructor.service;

import com.vocasia.instructor.dto.client.authentication.UserDto;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.request.UpdateProfileRequest;

public interface IAuthenticationService {

    UserDto registerNewUser(RegisterRequest registerRequest, String correlationId);
    UserDto getByUserId(Long userId, String correlationId);
    UserDto updateProfile(UpdateProfileRequest updateProfileRequest, String correlationId);

}
