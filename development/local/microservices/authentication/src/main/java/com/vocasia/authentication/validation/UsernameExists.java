package com.vocasia.authentication.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameExistsValidator.class)
public @interface UsernameExists {
    String message() default "Username sudah terdaftar";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

