package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotBlank;

public record ValidateDecorationDTO (
        @NotBlank(message = "decoration name is required")
        String name
) {
}
