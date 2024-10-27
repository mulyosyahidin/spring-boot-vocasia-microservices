package com.vocasia.course.validation;

import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.validation.annotation.ValidLessonContent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LessonContentValidator implements ConstraintValidator<ValidLessonContent, StoreLessonRequest> {

    @Override
    public boolean isValid(StoreLessonRequest request, ConstraintValidatorContext context) {
        if ("video".equalsIgnoreCase(request.getType())) {
            if (request.getContentVideoUrl() == null || request.getContentVideoUrl().isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("URL video harus diisi")
                        .addPropertyNode("contentVideoUrl")
                        .addConstraintViolation();
                return false;
            }

            if (request.getContentVideoDuration() == null || request.getContentVideoDuration().isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Durasi video harus diisi")
                        .addPropertyNode("contentVideoDuration")
                        .addConstraintViolation();
                return false;
            }
        }
        else {
            if (request.getContentText() == null || request.getContentText().isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Teks pelajaran harus diisi")
                        .addPropertyNode("contentText")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }

}

