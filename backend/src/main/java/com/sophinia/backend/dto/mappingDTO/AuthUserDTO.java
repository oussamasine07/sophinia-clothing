package com.sophinia.backend.dto.mappingDTO;

public record AuthUserDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
