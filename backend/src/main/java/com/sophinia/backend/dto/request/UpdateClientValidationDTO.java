package com.sophinia.backend.dto.request;

import com.sophinia.backend.validation.IsPasswordConfirmed;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@IsPasswordConfirmed
public record UpdateClientValidationDTO(
        @NotNull(message = "first name field is required")
        @Size(min = 1, message = "first name is required")
        String firstName,

        @NotNull(message = "last name field is required")
        @Size(min = 1, message = "last name is required")
        String lastName,

        @NotNull(message = "email field is required")
        @Size(min = 1, message = "email is required")
        @Email(message = "it should be a valid email")
        String email,

        @NotNull(message = "password field is required")
        @Size(min = 1, message = "password is required")
        String password,

        @NotNull(message = "confirm password field is required")
        @Size(min = 1, message = "confirm password is required")
        String confirmPassword
) {}
