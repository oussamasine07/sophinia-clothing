package com.sophinia.backend.service;

import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.response.MappedFabricDTO;
import com.sophinia.backend.dto.request.FabricFormDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.mapper.FabricMapper;
import com.sophinia.backend.model.Fabric;
import com.sophinia.backend.repository.FabricRepository;
import com.sophinia.backend.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FabricService {

    private final FabricRepository fabricRepository;
    private final FabricMapper fabricMapper;
    private final FileUpload fileUpload;
    private final OrderRepository orderRepository;

    public FabricService (
            final FabricRepository fabricRepository,
            final FabricMapper fabricMapper,
            final FileUpload fileUpload,
            final OrderRepository orderRepository
    ) {
        this.fabricRepository = fabricRepository;
        this.fabricMapper = fabricMapper;
        this.fileUpload = fileUpload;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<List<MappedFabricDTO>> getFabrics () {
        List<MappedFabricDTO> fabrics = fabricRepository.findAll()
                .stream()
                .map(this.fabricMapper::toDTO)
                .toList();

        return new ResponseEntity<>(fabrics, HttpStatus.OK);
    }

    public ResponseEntity<Fabric> getFabricById ( Long fabricId ) {
        Fabric fabric = fabricRepository.findById( fabricId )
                .orElseThrow(() -> new NotFoundException("unfound fabric"));

        return new ResponseEntity<>(fabric, HttpStatus.OK);
    }

    public ResponseEntity<Fabric> createNewFabric (FabricFormDTO fabricFormDTO) {
        Fabric fabric = new Fabric();

        fabric.setName(fabricFormDTO.name());
        fabric.setDescription( fabricFormDTO.description() );
        fabric.setPrice( fabricFormDTO.price() );

        if (fabricFormDTO.image() != null) {
            String image = fileUpload.upload( fabricFormDTO.image(), "fabrics");
            fabric.setImage(image);
        }

        Fabric newFabric = fabricRepository.save( fabric );
        return new ResponseEntity<>(newFabric, HttpStatus.OK);
    }

    public ResponseEntity<Fabric> updateFabric (FabricFormDTO fabricFormDTO, Long fabricId) {
        Fabric updatedFabric = fabricRepository.findById( fabricId )
                .orElseThrow(() -> new NotFoundException("you can't update an unfound fabric"));

        updatedFabric.setName(fabricFormDTO.name());
        updatedFabric.setDescription(fabricFormDTO.description());

        if (fabricFormDTO.image() != null) {
            String image = fileUpload.upload( fabricFormDTO.image(), "fabrics");
            updatedFabric.setImage(image);
        }

        updatedFabric = fabricRepository.save( updatedFabric );

        return new ResponseEntity<>( updatedFabric, HttpStatus.OK);

    }

    public ResponseEntity<Map<String, Object>> deleteFabric (Long fabricId) {
        if (orderRepository.existsByFabricId( fabricId )) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "you can't remove a design related to orders");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        Fabric deletedFabric = fabricRepository.findById( fabricId )
                .orElseThrow(() -> new NotFoundException("you can't delete an unfound fabric"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", deletedFabric.getName() + " has been Deleted");
        response.put("id", deletedFabric.getId());

        fabricRepository.deleteById( fabricId );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}











