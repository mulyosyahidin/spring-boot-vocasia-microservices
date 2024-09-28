package com.vocasia.authentication.service;

import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.request.RegisterRequest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IUserService {
    /**
     * @param keyCloackUid
     * @param registerRequest
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    User registerNewUser(String keyCloackUid, RegisterRequest registerRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;

    /**
     * @param email User's email to check
     * @return True if the email is already registered, false otherwise
     */
    boolean isEmailAlreadyRegistered(String email);

    /**
     * @param username User's username to check
     * @return True if the username is already registered, false otherwise
     */
    boolean isUsernameAlreadyRegistered(String username);

    /**
     * @param loginRequest
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    User loginWithEmailAndPassword(LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
