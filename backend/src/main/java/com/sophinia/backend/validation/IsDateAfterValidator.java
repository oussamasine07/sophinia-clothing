package com.sophinia.backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;

public class IsDateAfterValidator implements ConstraintValidator<IsDateAfter, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        try {
            BeanWrapperImpl wrapper = new BeanWrapperImpl(obj);
            LocalDate startDate = (LocalDate) wrapper.getPropertyValue("startDate");
            LocalDate endDate = (LocalDate) wrapper.getPropertyValue("endDate");

            if (startDate == null || endDate == null ) {
                return true;
            }

            boolean isBefore = startDate.isBefore(endDate);
            if (!isBefore) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("date should be after previouse date")
                        .addPropertyNode("endDate") // Associate error with confirmPassword field
                        .addConstraintViolation();
            }

            return isBefore;
        }
        catch (Exception e) {
            return true;
        }
    }
}
