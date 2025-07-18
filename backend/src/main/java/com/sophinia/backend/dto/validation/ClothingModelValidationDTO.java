package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ClothingModelValidationDTO (
        @NotEmpty(message = "name field is required")
        @NotBlank(message = "name field is required")
        String name,

        String image
) {
}

