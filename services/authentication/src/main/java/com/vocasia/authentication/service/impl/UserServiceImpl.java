package com.vocasia.authentication.service.impl;

import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.repository.UserRepository;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.request.RegisterRequest;
import com.vocasia.authentication.service.IUserService;
import com.vocasia.authentication.util.PasswordHashUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private PasswordHashUtil passwordHashUtil;

    @Override
    public User registerNewUser(String keyCloackUid, RegisterRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String encryptedPassword = passwordHashUtil.hashPassword(request.getPassword());
        User user = new User();

        user.setUid(keyCloackUid);
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setRole(request.getRole());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return user;
    }

    @Override
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUsernameAlreadyRegistered(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User loginWithEmailAndPassword(LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            return null;
        }

        if (passwordHashUtil.validatePassword(loginRequest.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
