package com.sophinia.backend.dto.validation;

import com.sophinia.backend.validation.IsPasswordConfirmed;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@IsPasswordConfirmed
public record UpdateClientValidationDTO(
        @NotBlank(message = "first name is required")
        String firstName,

        @NotBlank(message = "last name is required")
        String lastName,

        @NotBlank(message = "email is required")
        @Email(message = "it should be a valid email")
        String email,

        @NotBlank(message = "password is required")
        String password,

        @NotBlank(message = "confirm password is required")
        String confirmPassword
) {}
