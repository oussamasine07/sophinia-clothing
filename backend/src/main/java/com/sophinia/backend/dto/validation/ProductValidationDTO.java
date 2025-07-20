package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProductValidationDTO(

        @NotBlank(message = "name field is required")
        @NotEmpty(message = "name field is required")
        String name,

        @NotBlank(message = "description field is required")
        @NotEmpty(message = "description field is required")
        String description,

        @NotNull(message = "choose a clothing type")
        Long clothing_type,

        List<Long> measurements_fields_ids,

        List<MeasurementFieldValidationDTO> measurement_fields,

        String image

) {}
