package com.vocasia.enrollment.service;

import com.vocasia.enrollment.dto.client.authentication.UserDto;

public interface IAuthenticationService {

    UserDto getByUserId(Long userId, String correlationId);

}
