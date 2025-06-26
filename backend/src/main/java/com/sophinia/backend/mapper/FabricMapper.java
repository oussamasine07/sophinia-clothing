package com.sophinia.backend.mapper;

import com.sophinia.backend.dto.MappedFabricDTO;
import com.sophinia.backend.model.Fabric;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FabricMapper {
    Fabric toEntity( MappedFabricDTO mappedFabricDTO );
    MappedFabricDTO toDTO(Fabric fabric);
}
