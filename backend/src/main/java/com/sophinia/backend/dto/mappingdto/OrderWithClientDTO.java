package com.sophinia.backend.dto.mappingdto;

public record OrderWithClientDTO(
        Long orderId,
        String status,
        String firstName,
        String lastName,
        String productName
) {}
