package com.sophinia.backend.mapper;

import com.sophinia.backend.dto.mappingdto.AuthUserDTO;
import com.sophinia.backend.model.UserPrincipal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AuthUserDTO toDTO(UserPrincipal userPrincipal);
}
