package com.vocasia.authentication.service.impl;

import com.vocasia.authentication.mapper.AccessTokenMapper;
import com.vocasia.authentication.packages.keycloak.KeyCloakClient;
import com.vocasia.authentication.service.IKeyCloackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class KeyCloackServiceImpl implements IKeyCloackService  {

    private KeyCloakClient keyCloackClient;

    @Override
    public String registerNewUser(String email, String username, String password, String name, String roleName) {
        return keyCloackClient.registerNewUser(email, username, password, name, roleName);
    }

    @Override
    public AccessTokenMapper getAccessToken(String username, String password) throws IOException {
        return keyCloackClient.getAccessToken(username, password);
    }

    @Override
    public AccessTokenMapper refreshAccessToken(String refreshToken) throws IOException {
        return keyCloackClient.refreshAccessToken(refreshToken);
    }

    @Override
    public boolean isUserExists(String uid) {
        return keyCloackClient.isUserExists(uid);
    }

}
