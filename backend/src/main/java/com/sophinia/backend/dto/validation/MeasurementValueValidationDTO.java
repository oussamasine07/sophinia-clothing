package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotBlank;

public record MeasurementValueValidationDTO (
        @NotBlank(message = "measurement set id is required")
        Long measurementSetId,

        @NotBlank(message = "measurement set id is required")
        Long measurementFieldId,

        @NotBlank(message = "measurement set id is required")
        Double value
){}
