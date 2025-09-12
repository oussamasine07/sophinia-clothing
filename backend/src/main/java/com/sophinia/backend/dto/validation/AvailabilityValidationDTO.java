package com.sophinia.backend.dto.validation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sophinia.backend.model.AvailabilityType;
import com.sophinia.backend.validation.IsDateAfter;
import com.sophinia.backend.validation.NextDate;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@IsDateAfter
public record AvailabilityValidationDTO(

        @NotNull(message = "start date is required")
        @NextDate
        @JsonFormat(pattern = "yyyy-M-d")
        LocalDate startDate,

        @NotNull(message = "end date is required")
        @NextDate
        @JsonFormat(pattern = "yyyy-M-d")
        LocalDate endDate,

        @NotNull(message = "availabliti is required or invalid one")
        AvailabilityType availabilityType

) {}
