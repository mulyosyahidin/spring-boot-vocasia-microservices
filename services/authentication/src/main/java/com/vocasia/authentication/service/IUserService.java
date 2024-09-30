package com.vocasia.authentication.service;

import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.request.RegisterRequest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IUserService {

    User registerNewUser(String keyCloackUid, RegisterRequest registerRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;
    boolean isEmailAlreadyRegistered(String email);
    boolean isUsernameAlreadyRegistered(String username);
    User loginWithEmailAndPassword(LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;

    User findById(Long id);

}
