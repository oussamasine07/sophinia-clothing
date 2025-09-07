package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MeasurementsValuesDTO (

        @NotNull(message = "you need to insert measurements values")
        List<MeasurementValueValidationDTO> measurementValues

){}
