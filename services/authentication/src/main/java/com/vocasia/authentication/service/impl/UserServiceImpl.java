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

    /**
     * @param request New user's data
     */
    @Override
    public User registerNewUser(String keyCloackUid, RegisterRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String encryptedPassword = passwordHashUtil.hashPassword(request.getPassword());
        User user = new User();

        user.setUid(keyCloackUid);
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return user;
    }

    /**
     * @param email User's email to check
     * @return True if the email is already registered, false otherwise
     */
    @Override
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * @param username User's username to check
     * @return True if the username is already registered, false otherwise
     */
    @Override
    public boolean isUsernameAlreadyRegistered(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * @param loginRequest
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
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
}
