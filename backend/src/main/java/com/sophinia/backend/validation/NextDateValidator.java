package com.sophinia.backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class NextDateValidator  implements ConstraintValidator<NextDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            // Disable the default violation
            constraintValidatorContext.disableDefaultConstraintViolation();
            // Add a custom violation message
            constraintValidatorContext.buildConstraintViolationWithTemplate("date cannot be blank")
                    .addConstraintViolation();
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        return !date.isBefore(currentDate);
    }
}