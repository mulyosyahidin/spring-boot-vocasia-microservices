package com.vocasia.order.service;

import com.vocasia.order.dto.client.authentication.UserDto;

public interface IAuthenticationService {

    UserDto findUserById(Long userId, String correlationId);

}
