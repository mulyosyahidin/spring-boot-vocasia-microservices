package com.vocasia.finance.service;

import com.vocasia.finance.dto.client.authentication.UserDto;

public interface IAuthenticationService {

    UserDto findUserById(Long userId, String correlationId);

}
