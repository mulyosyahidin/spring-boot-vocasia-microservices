package com.vocasia.instructor.service;

import com.vocasia.instructor.dto.client.authentication.UserDto;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.request.UpdateProfileRequest;

public interface IAuthenticationService {

    UserDto registerUser(RegisterRequest registerRequest, String correlationId);
    UserDto findUserById(Long userId, String correlationId);
    UserDto updateUser(UpdateProfileRequest updateProfileRequest, String correlationId);

}
