package com.sophinia.backend.mapper;


import com.sophinia.backend.dto.mappingdto.AuthUserDTO;
import com.sophinia.backend.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(AuthUserDTO authUserDTO);
    AuthUserDTO toDTO(Client client);
}
