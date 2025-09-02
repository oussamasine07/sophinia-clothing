package com.sophinia.backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class NextDateValidator  implements ConstraintValidator<NextDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate currentDate = LocalDate.now();
        return !date.isBefore(currentDate);
    }
}