package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record MeasurementFieldValidationDTO(
        @NotBlank(message = "measurement name is reauired")
        @NotEmpty(message = "measurement name is reauired")
        String name
) {
}
