package com.vocasia.authentication.service.impl;

import com.vocasia.authentication.config.AwsConfigProperties;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.exception.ResourceNotFoundException;
import com.vocasia.authentication.packages.aws.service.IAwsService;
import com.vocasia.authentication.repository.UserRepository;
import com.vocasia.authentication.request.LoginRequest;
import com.vocasia.authentication.request.RegisterRequest;
import com.vocasia.authentication.request.UpdateProfileRequest;
import com.vocasia.authentication.service.IUserService;
import com.vocasia.authentication.util.PasswordHashUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    private final AwsConfigProperties awsConfigProperties;
    private final IAwsService awsService;
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
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Data tidak ditemukan")
        );
    }

    @Override
    public List<User> getByRole(String role) {
        return userRepository.getByRole(role);
    }

    @Override
    public User updateProfile(User user, UpdateProfileRequest updateUserRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        if (updateUserRequest.getName() != null) {
            user.setName(updateUserRequest.getName());
        }

        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }

        if (updateUserRequest.getPassword() != null) {
            String encryptedPassword = passwordHashUtil.hashPassword(updateUserRequest.getPassword());
            user.setPassword(encryptedPassword);
        }

        if (updateUserRequest.getProfilePicture() != null) {
            String bucketName = awsConfigProperties.getS3().getBucket();

            if (user.getProfilePicture() != null) {
                awsService.deleteFile(bucketName, user.getProfilePicture());
            }

            MultipartFile profilePicture = updateUserRequest.getProfilePicture();
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(profilePicture.getOriginalFilename()));
            String contentType = profilePicture.getContentType();
            long fileSize = profilePicture.getSize();
            InputStream inputStream = profilePicture.getInputStream();

            awsService.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);

            user.setProfilePicture(fileName);
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public Page<User> findAllByRole(String role, Pageable paging) {
        return userRepository.findAllByRole(role, paging);
    }

}
