package com.sophinia.backend.service;


import com.sophinia.backend.model.ClothingModel;
import com.sophinia.backend.repository.ClothingModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClothingModelService {

    private final ClothingModelRepository clothingModelRepository;

    public ClothingModelService (
            final ClothingModelRepository clothingModelRepository
    ) {
        this.clothingModelRepository = clothingModelRepository;
    }

    public ResponseEntity<?> createClothingModel (ClothingModel clothingModel) {

        ClothingModel savedClothingModel = clothingModelRepository.save( clothingModel );

        return new ResponseEntity<>( savedClothingModel, HttpStatus.OK );

    }

}
























