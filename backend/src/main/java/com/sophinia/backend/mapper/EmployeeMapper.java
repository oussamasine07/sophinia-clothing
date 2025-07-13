package com.sophinia.backend.mapper;


import com.sophinia.backend.dto.mappingDTO.AuthUserDTO;
import com.sophinia.backend.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(AuthUserDTO authUserDTO);
    AuthUserDTO toDTO(Employee employee);
}
