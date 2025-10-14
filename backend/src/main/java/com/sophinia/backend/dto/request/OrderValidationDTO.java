package com.sophinia.backend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record OrderValidationDTO(
        @NotNull(message = "product is required")
        Long productId,

        @NotNull(message = "clothing model is required")
        Long clothingModelId,

        @NotNull(message = "fabric is required")
        Long fabricId,

        @NotNull(message = "decoration is required")
        Long decorationId,

        @NotNull(message = "design is required")
        Long designId,

        @Valid
        @NotNull(message = "client details are required is required")
        ClientValidationDTO client,

        @Valid
        @NotNull(message = "availability is reauired")
        AvailabilityValidationDTO availability
) {}
