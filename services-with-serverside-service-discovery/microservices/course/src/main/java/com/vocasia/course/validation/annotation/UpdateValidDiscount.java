package com.vocasia.course.validation.annotation;

import com.vocasia.course.validation.UpdateDiscountValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UpdateDiscountValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateValidDiscount {

    String message() default "Diskon tidak boleh lebih besar dari harga kursus";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
