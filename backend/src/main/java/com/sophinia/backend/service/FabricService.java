package com.sophinia.backend.service;

import com.sophinia.backend.dto.FabricFormDTO;
import com.sophinia.backend.dto.MappedFabricDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.mapper.FabricMapper;
import com.sophinia.backend.model.Fabric;
import com.sophinia.backend.repository.FabricRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FabricService {

    private final FabricRepository fabricRepository;
    private final FabricMapper fabricMapper;

    public FabricService (
            final FabricRepository fabricRepository,
            final FabricMapper fabricMapper
    ) {
        this.fabricRepository = fabricRepository;
        this.fabricMapper = fabricMapper;
    }

    public ResponseEntity<?> getFabrics () {
        List<MappedFabricDTO> fabrics = fabricRepository.findAll()
                .stream()
                .map(this.fabricMapper::toDTO)
                .toList();

        return new ResponseEntity<>(fabrics, HttpStatus.OK);
    }

    public ResponseEntity<?> createNewFabric (Fabric fabric) {
        Fabric newFabric = fabricRepository.save( fabric );
        return new ResponseEntity<>(newFabric, HttpStatus.OK);
    }

    public ResponseEntity<?> updateFabric (Fabric fabric, Long fabricId) {
        Fabric updatedFabric = fabricRepository.findById( fabricId )
                .orElseThrow(() -> new NotFoundException("you can't update an unfound fabric"));

        updatedFabric.setName(fabric.getName());
        updatedFabric.setDescription(fabric.getDescription());

        updatedFabric = fabricRepository.save( updatedFabric );

        return new ResponseEntity<>( updatedFabric, HttpStatus.OK);

    }

    public ResponseEntity<?> deleteFabric (Long fabricId) {
        Fabric deletedFabric = fabricRepository.findById( fabricId )
                .orElseThrow(() -> new NotFoundException("you can't delete an unfound fabric"));

        fabricRepository.deleteById( fabricId );

        return new ResponseEntity<>(deletedFabric, HttpStatus.OK);
    }


}











