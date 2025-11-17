package com.sophinia.backend.dto.request;

import com.sophinia.backend.validation.IsEmailAlreadyExists;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeUpdateValidationDTO(
        @NotBlank(message = "first name is required")
        String firstName,

        @NotBlank(message = "last name is required")
        String lastName,

        @NotBlank(message = "email is required")
        @Email(message = "in valid email")
        String email,

        String password
) {
}
