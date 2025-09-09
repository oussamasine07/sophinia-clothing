package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.*;

public record LoginValidationDTO(
        @NotNull(message = "email field is required")
        @Size(min = 1, message = "email is required")
        @Email(message = "please make sure it's a valid email")
        String email,

        @NotNull(message = "password field is required")
        @Size(min = 1, message = "password is required")
        String password
) {
}
