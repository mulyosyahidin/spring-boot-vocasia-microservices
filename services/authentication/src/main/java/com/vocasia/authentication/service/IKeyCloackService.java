package com.vocasia.authentication.service;

import java.io.IOException;
import java.util.Map;

public interface IKeyCloackService {
    /**
     * @param email
     * @param username
     * @param password
     * @param name
     * @param roleName
     * @return String Registered keycloack id
     */
    String registerNewUser(String email, String username, String password, String name, String roleName);

    /**
     * @param username
     * @param password
     * @return Map<String, String>
     */
    Map<String, String> getAccessToken(String username, String password) throws IOException;
}
