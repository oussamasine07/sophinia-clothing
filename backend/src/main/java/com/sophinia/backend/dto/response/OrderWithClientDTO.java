package com.sophinia.backend.dto.response;

public record OrderWithClientDTO(
        Long orderId,
        String status,
        String firstName,
        String lastName,
        String productName
) {}
