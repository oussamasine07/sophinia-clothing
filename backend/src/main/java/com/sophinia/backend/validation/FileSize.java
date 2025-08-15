package com.sophinia.backend.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {
    String message() default "make sure you have a valid size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    long max(); // in bytes
    long min() default 0;
}
