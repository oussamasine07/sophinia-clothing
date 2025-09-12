package com.sophinia.backend.dto.validation;

import com.sophinia.backend.validation.FileSize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record DesignValidateDTO(
        @NotNull(message = "name field is required")
        @Size(min = 1, message = "name is required")
        String name,

        @FileSize(max = 5 * 1024 * 1024, message = "File too large")
        MultipartFile image

) {
}
