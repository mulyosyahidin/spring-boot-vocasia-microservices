package com.vocasia.instructor.service;

import com.vocasia.instructor.dto.feign.UserDto;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.request.UpdateProfileRequest;

public interface IAuthenticationService {

    UserDto registerNewUser(RegisterRequest registerRequest, String correlationId);
    UserDto getByUserId(Long userId);
    UserDto updateProfile(UpdateProfileRequest updateProfileRequest);

}
