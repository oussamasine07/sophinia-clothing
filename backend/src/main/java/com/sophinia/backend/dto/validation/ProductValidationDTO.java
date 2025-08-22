package com.sophinia.backend.dto.validation;

import com.sophinia.backend.validation.FileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

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

        @FileSize(max = 5 * 1024 * 1024, message = "File too large")
        MultipartFile image,

        List<Long> measurements_fields_ids,

        List<MeasurementFieldValidationDTO> measurement_fields

) {}
