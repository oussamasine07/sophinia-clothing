package com.sophinia.backend.mapper;

import com.sophinia.backend.dto.mappingDTO.AuthUserDTO;
import com.sophinia.backend.model.Admin;
import com.sophinia.backend.model.UserPrincipal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //UserPrincipal toEntity(AuthUserDTO authUserDTO);
    AuthUserDTO toDTO(UserPrincipal userPrincipal);
}
