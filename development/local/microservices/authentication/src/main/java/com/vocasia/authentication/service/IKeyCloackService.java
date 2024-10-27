package com.vocasia.authentication.service;

import com.vocasia.authentication.mapper.AccessTokenMapper;

import java.io.IOException;

public interface IKeyCloackService {

    String registerNewUser(String email, String username, String password, String name, String roleName);
    AccessTokenMapper getAccessToken(String username, String password) throws IOException;
    AccessTokenMapper refreshAccessToken(String accessToken) throws IOException;
    boolean isUserExists(String uid);

}
