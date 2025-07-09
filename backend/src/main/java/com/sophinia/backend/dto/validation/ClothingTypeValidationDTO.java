package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotBlank;

public record ClothingTypeValidationDTO(
        @NotBlank(message = "name field is required")
        String name,

        String image
) {
}
