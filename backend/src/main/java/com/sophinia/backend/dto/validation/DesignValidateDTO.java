package com.sophinia.backend.dto.validation;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record DesignValidateDTO(
        @NotBlank(message = "Name is required")
        String name,

//        @NotNull(message = "File is required")
//        @FileSize(max = 5 * 1024 * 1024, message = "File too large")
        MultipartFile image

) {
}
