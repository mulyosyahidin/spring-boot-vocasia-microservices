package com.vocasia.qna.service;

import com.vocasia.qna.dto.client.authentication.UserDto;

public interface IAuthenticationService {

    UserDto findUserById(Long userId, String correlationId);

}

