package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record LoginValidationDTO(
        @NotEmpty(message = "email is reauired")
        @NotBlank(message = "email is reauired")
        @Email(message = "please make sure it's a valid email")
        String email,

        @NotEmpty(message = "password is reauired")
        @NotBlank(message = "password is reauired")
        String password
) {
}
