package com.vocasia.authentication.service.impl;

import com.vocasia.authentication.packages.keycloack.KeyCloackClient;
import com.vocasia.authentication.service.IKeyCloackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class KeyCloackServiceImpl implements IKeyCloackService  {

    private KeyCloackClient keyCloackClient;

    /**
     * @param email
     * @param username
     * @param password
     * @param name
     * @param roleName
     */
    @Override
    public String registerNewUser(String email, String username, String password, String name, String roleName) {
        return keyCloackClient.registerNewUser(email, username, password, name, roleName);
    }

    /**
     * @param username
     * @param password
     * @return Map<String, String>
     */
    @Override
    public Map<String, Object> getAccessToken(String username, String password) throws IOException {
        return keyCloackClient.getAccessToken(username, password);
    }
}
