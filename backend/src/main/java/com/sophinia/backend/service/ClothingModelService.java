package com.sophinia.backend.service;


import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.ClothingModel;
import com.sophinia.backend.repository.ClothingModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClothingModelService {

    private final ClothingModelRepository clothingModelRepository;

    public ClothingModelService (
            final ClothingModelRepository clothingModelRepository
    ) {
        this.clothingModelRepository = clothingModelRepository;
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

    public ResponseEntity<?> createClothingModel (ClothingModel clothingModel) {

        ClothingModel savedClothingModel = clothingModelRepository.save( clothingModel );

        return new ResponseEntity<>( savedClothingModel, HttpStatus.OK );

    }

    public ResponseEntity<?> updateClothingModel ( ClothingModel clothingModel, Long id) {
        ClothingModel foundClothingModel = clothingModelRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this clothing model is not found"));

        foundClothingModel.setName( clothingModel.getName() );
        foundClothingModel.setImage( clothingModel.getImage() );

        return new ResponseEntity<>( clothingModelRepository.save( foundClothingModel ), HttpStatus.OK );
    }

    public ResponseEntity<?> deleteClothingModel ( Long id ) {
        ClothingModel foundClothingModel = clothingModelRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this clothing model is not found"));

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", foundClothingModel.getName() + " has been Deleted");

        clothingModelRepository.deleteById( id );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
























