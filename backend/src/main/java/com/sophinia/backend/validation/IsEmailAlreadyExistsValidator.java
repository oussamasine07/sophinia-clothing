package com.sophinia.backend.validation;


import com.sophinia.backend.model.User;
import com.sophinia.backend.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class IsEmailAlreadyExistsValidator implements ConstraintValidator<IsEmailAlreadyExists, String> {

    private final UserRepository userRepository;
    public IsEmailAlreadyExistsValidator (
            final UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("email validation is running");
        Optional< User > found = userRepository.findByEmail( email );

        return found.isEmpty();
    }

}
