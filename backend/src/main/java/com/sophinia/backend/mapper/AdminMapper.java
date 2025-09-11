package com.sophinia.backend.mapper;

import com.sophinia.backend.dto.mappingdto.AuthUserDTO;
import com.sophinia.backend.model.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toEntity(AuthUserDTO authUserDTO);
    AuthUserDTO toDTO(Admin admin);

}
