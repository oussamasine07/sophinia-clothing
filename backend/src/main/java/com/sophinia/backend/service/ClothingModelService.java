package com.sophinia.backend.service;


import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.validation.ClothingModelValidationDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.ClothingModel;
import com.sophinia.backend.repository.ClothingModelRepository;
import com.sophinia.backend.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClothingModelService {

    private final ClothingModelRepository clothingModelRepository;
    private final FileUpload fileUpload;
    private final OrderRepository orderRepository;

    public ClothingModelService (
            final ClothingModelRepository clothingModelRepository,
            final FileUpload fileUpload,
            final OrderRepository orderRepository
    ) {
        this.clothingModelRepository = clothingModelRepository;
        this.fileUpload = fileUpload;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<?> getAllClothingModels () {
        List<ClothingModel> clothingModels = clothingModelRepository.findAll();

        return new ResponseEntity<>( clothingModels, HttpStatus.OK );
    }

    public ResponseEntity<?> getClothingModel ( Long id ) {
        ClothingModel clothingModel = clothingModelRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this clothing model is not found"));

        return new ResponseEntity<>(clothingModel, HttpStatus.OK);
    }

    public ResponseEntity<?> createClothingModel ( ClothingModelValidationDTO clothingModelValidationDTO ) {
        ClothingModel clothingModel = new ClothingModel();

        clothingModel.setName( clothingModelValidationDTO.name() );
        if ( clothingModelValidationDTO.image() != null) {
            String image = fileUpload.upload( clothingModelValidationDTO.image(), "clothing-model");
            clothingModel.setImage(image);
        }

        ClothingModel savedClothingModel = clothingModelRepository.save( clothingModel );

        return new ResponseEntity<>( savedClothingModel, HttpStatus.OK );

    }

    public ResponseEntity<?> updateClothingModel ( ClothingModelValidationDTO clothingModelValidationDTO, Long id) {
        ClothingModel foundClothingModel = clothingModelRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this clothing model is not found"));

        foundClothingModel.setName( clothingModelValidationDTO.name() );
        if ( clothingModelValidationDTO.image() != null) {
            String image = fileUpload.upload( clothingModelValidationDTO.image(), "clothing-model");
            foundClothingModel.setImage(image);
        }

        return new ResponseEntity<>( clothingModelRepository.save( foundClothingModel ), HttpStatus.OK );
    }

    public ResponseEntity<?> deleteClothingModel ( Long id ) {
        if (orderRepository.existsByClothingModelId(id)) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "you can't remove a design related to orders");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        ClothingModel foundClothingModel = clothingModelRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this clothing model is not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", foundClothingModel.getName() + " has been Deleted");
        response.put("id", foundClothingModel.getId());

        clothingModelRepository.deleteById( id );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
























