package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.*;

public record ClientValidationDTO (
        @NotNull(message = "first name is required")
        @Size(min = 1, message = "first name is required")
        String firstName,

        @NotNull(message = "last name is required")
        @Size(min = 1, message = "last name is required")
        String lastName,

        @NotNull(message = "email is required")
        @Size(min = 1, message = "email is required")
        @Email(message = "it should be a valid email")
        String email,

        @NotNull(message = "phone is required")
        @Size(min = 1, message = "phone is required")
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
