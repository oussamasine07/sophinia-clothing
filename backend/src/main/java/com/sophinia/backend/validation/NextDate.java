package com.sophinia.backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NextDateValidator.class)
public @interface NextDate {

    String message() default "Please enter a valid date that is today or later.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
