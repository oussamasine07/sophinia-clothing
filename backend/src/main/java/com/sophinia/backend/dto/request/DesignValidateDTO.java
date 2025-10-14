package com.sophinia.backend.dto.request;

import com.sophinia.backend.validation.FileSize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record DesignValidateDTO(
        @NotNull(message = "name field is required")
        @Size(min = 1, message = "name is required")
        String name,

        @FileSize(max = 5 * 1024 * 1024, message = "File too large")
        MultipartFile image,

        @NotNull(message = "description field is required")
        @Size(min = 1, message = "description is required")
        String description,

        @NotNull(message = "cost field is required")
        Double cost,

        @NotNull(message = "decorations is required")
        Long decoration_id


) {
}
