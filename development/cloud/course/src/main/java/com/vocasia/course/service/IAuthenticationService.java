package com.vocasia.course.service;

import com.vocasia.course.dto.client.authentication.UserDto;

public interface IAuthenticationService {

    UserDto findUserById(Long userId, String correlationId);

}

