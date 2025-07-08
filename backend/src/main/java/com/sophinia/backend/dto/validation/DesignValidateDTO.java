package com.sophinia.backend.dto.validation;

import org.hibernate.validator.constraints.NotBlank;

public record DesignValidateDTO(
        @NotBlank(message = "name field is required")
        String name,

        String image
) {
}
