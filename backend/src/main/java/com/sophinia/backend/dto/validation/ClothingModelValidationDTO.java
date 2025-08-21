package com.sophinia.backend.dto.validation;

import com.sophinia.backend.validation.FileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public record ClothingModelValidationDTO (
        @NotEmpty(message = "name field is required")
        @NotBlank(message = "name field is required")
        String name,

        @FileSize(max = 5 * 1024 * 1024, message = "File too large")
        MultipartFile image
) {
}

