package com.vocasia.course.validation;

import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.validation.annotation.UpdateValidDiscount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpdateDiscountValidator implements ConstraintValidator<UpdateValidDiscount, UpdateCourseRequest> {

    @Override
    public boolean isValid(UpdateCourseRequest request, ConstraintValidatorContext context) {
        if (request.getPrice() == null || request.getPrice() <= 0) {
            if (request.getDiscount() != null && request.getDiscount() > 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Tidak boleh memberikan diskon jika kursus ini gratis")
                        .addPropertyNode("discount")
                        .addConstraintViolation();
                return false;
            }

            return true;
        }

        if (request.getDiscount() != null && request.getDiscount() > request.getPrice()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Diskon tidak boleh lebih besar dari harga.")
                    .addPropertyNode("discount")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
