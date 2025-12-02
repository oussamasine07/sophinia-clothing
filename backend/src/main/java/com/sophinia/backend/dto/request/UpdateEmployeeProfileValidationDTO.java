package com.sophinia.backend.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateEmployeeProfileValidationDTO(
        String firstName,
        String lastName,
        @Email(message = "in valid email")
        String email,
        String password,
        String phone,
        String address,
        String city,
        String postalCode,
        String cnssCode
) {
}
