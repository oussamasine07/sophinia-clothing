package com.sophinia.backend.dto.mappingDTO;

public record OrderWithClientDTO(
        Long orderId,
        String status,
        String firstName,
        String lastName,
        String productName
) {}
