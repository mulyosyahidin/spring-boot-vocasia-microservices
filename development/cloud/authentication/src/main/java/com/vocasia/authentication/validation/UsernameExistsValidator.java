package com.vocasia.authentication.validation;

import com.vocasia.authentication.service.IUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameExistsValidator implements ConstraintValidator<UsernameExists, String> {

    @Autowired
    private IUserService IUserService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !IUserService.isUsernameAlreadyRegistered(username);
    }
}

