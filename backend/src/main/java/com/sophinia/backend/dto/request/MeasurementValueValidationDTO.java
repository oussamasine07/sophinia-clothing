package com.sophinia.backend.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MeasurementValueValidationDTO (
        @NotNull(message = "measurement set field is required")
        @Size(min = 1, message = "measurement set is required")
        Long measurementSetId,

        @NotNull(message = "measurement id field is required")
        @Size(min = 1, message = "measurement set is required")
        Long measurementFieldId,

        @NotNull(message = "measurement value is required")
        @Size(min = 1, message = "measurement value is required")
        Double value
){}
