package com.vocasia.instructor.service;

import com.vocasia.instructor.dto.UserDto;
import com.vocasia.instructor.request.RegisterRequest;

public interface IAuthenticationService {

    UserDto registerNewUser(RegisterRequest registerRequest, String correlationId);

}
