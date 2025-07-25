package com.sophinia.backend.service;

import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.ClothingType;
import com.sophinia.backend.repository.ClothingTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClothingTypeService {

    private final ClothingTypeRepository clothingTypeRepository;

    public ClothingTypeService (
            final ClothingTypeRepository clothingTypeRepository
    ) {
        this.clothingTypeRepository = clothingTypeRepository;
    }

    public ResponseEntity<?> getAllClothingTypes () {

        List<ClothingType> clothingTypes = clothingTypeRepository.findAll();

        return new ResponseEntity<>(clothingTypes, HttpStatus.OK);

    }

    public ResponseEntity<?> getClothingTypeById ( Long id ) {
        ClothingType clothingType = clothingTypeRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("Unfound clothing type"));

        return new ResponseEntity<>(clothingType, HttpStatus.OK);
    }

    public ResponseEntity<?> createClothingType ( ClothingType clothingType ) {
        return new ResponseEntity<>( clothingTypeRepository.save(clothingType), HttpStatus.OK );
    }

    public ResponseEntity<?> updateClothingType ( ClothingType clothingType, Long id ) {

        ClothingType updatedClothingType = clothingTypeRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("you can't update an not found clothing type"));

        updatedClothingType.setName(clothingType.getName());
        updatedClothingType.setImage( clothingType.getImage());

        return new ResponseEntity<>( clothingTypeRepository.save( updatedClothingType ), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteClothingType ( Long id ) {
        ClothingType clothingType = clothingTypeRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("you can't update an not found clothing type"));

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", clothingType.getName() + " has been Deleted");

        clothingTypeRepository.deleteById( id );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}




















