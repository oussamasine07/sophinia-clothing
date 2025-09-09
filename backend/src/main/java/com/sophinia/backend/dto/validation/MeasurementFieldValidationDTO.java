package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MeasurementFieldValidationDTO(
        @NotNull(message = "name field is required")
        @Size(min = 1, message = "name is required")
        String name
) {
}
