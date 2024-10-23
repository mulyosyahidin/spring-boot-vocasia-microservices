package com.vocasia.authentication.validation;

import com.vocasia.authentication.service.IUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {

    @Autowired
    private IUserService IUserService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !IUserService.isEmailAlreadyRegistered(email);
    }
}

