package com.sophinia.backend.dto.validation;

import com.sophinia.backend.validation.FileSize;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record FabricFormDTO(
        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "description is required")
        String description,

        @FileSize(max = 5 * 1024 * 1024, message = "File too large")
        MultipartFile image
) {
}
