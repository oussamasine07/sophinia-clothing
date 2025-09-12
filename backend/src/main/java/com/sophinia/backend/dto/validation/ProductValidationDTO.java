package com.sophinia.backend.dto.validation;

import com.sophinia.backend.validation.FileSize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductValidationDTO(

        @NotNull(message = "name field is required")
        @Size(min = 1, message = "name is required")
        String name,

        @NotNull(message = "description field is required")
        @Size(min = 1, message = "description is required")
        String description,

        @NotNull(message = "choose a clothing type")
        Long clothing_type,

        @FileSize(max = 5 * 1024 * 1024, message = "File too large")
        MultipartFile image,

        List<Long> measurements_fields_ids,

        List<MeasurementFieldValidationDTO> measurement_fields

) {}
