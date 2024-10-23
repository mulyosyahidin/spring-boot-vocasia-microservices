package com.vocasia.authentication.util;

import com.vocasia.authentication.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class PasswordHashUtil {

    @Autowired
    private SecurityProperties securityProperties;

    // Configuration parameters
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    // Method to hash the password
    public String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String salt = securityProperties.getSalt();

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hashedPassword = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // Method to verify the password
    public boolean verifyPassword(String password, String storedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hashToVerify = hashPassword(password);
        return hashToVerify.equals(storedHash);
    }

    public boolean validatePassword(String password, String savedUserPassword) {
        try {
            return verifyPassword(password, savedUserPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return false;
        }
    }
}
