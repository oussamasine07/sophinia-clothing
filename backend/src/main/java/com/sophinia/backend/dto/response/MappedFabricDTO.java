package com.sophinia.backend.dto.response;

public record MappedFabricDTO(
        Long id,
        String name,
        String description,
        String image
) {
}
