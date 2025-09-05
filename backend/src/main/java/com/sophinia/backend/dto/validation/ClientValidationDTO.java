package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientValidationDTO (
        @NotBlank(message = "first name is required")
        String firstName,

        @NotBlank(message = "last name is required")
        String lastName,

        @NotBlank(message = "email is required")
        @Email(message = "it should be a valid email")
        String email,

        @NotBlank(message = "phone is required")
        @Pattern(
                regexp = "^((06)|(05)|(07))([0-9]{8})$",
                message = "Invalid phone number"
        )
        String phone,

        @Pattern(
                regexp = "^[a-zA-Z0-9,.]*$",
                message = "address must contain only letters, digits, commas, or full stops"
        )
        String address
) {}
