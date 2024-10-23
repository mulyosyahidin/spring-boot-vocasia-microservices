package com.vocasia.authentication.service;

import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.request.RegisterRequest;
import com.vocasia.authentication.request.UpdateProfileRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface IUserService {

    User registerNewUser(String keyCloackUid, RegisterRequest registerRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;
    boolean isEmailAlreadyRegistered(String email);
    boolean isUsernameAlreadyRegistered(String username);
    User loginWithEmailAndPassword(LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;

    User findById(Long id);
    List<User> getByRole(String role);

    User updateProfile(User user, UpdateProfileRequest updateUserRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException;

    Page<User> findAllByRole(String role, Pageable paging);

    User updateUid(Long id, String registeredKeycloakId);

}
