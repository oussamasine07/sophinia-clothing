package com.sophinia.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record FabricFormDTO(
        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "description is required")
        String description
) {
}
