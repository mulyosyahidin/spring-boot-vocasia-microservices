package com.vocasia.course.validation.annotation;

import com.vocasia.course.validation.LessonContentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LessonContentValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLessonContent {
    String message() default "Konten pelajaran tidak valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
