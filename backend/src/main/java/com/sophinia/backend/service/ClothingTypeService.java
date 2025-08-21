package com.sophinia.backend.service;

import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.validation.ClothingTypeValidationDTO;
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
    private final FileUpload fileUpload;

    public ClothingTypeService (
            final ClothingTypeRepository clothingTypeRepository,
            final FileUpload fileUpload
    ) {
        this.clothingTypeRepository = clothingTypeRepository;
        this.fileUpload = fileUpload;
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

    public ResponseEntity<?> createClothingType ( ClothingTypeValidationDTO clothingTypeValidationDTO ) {
        ClothingType clothingType = new ClothingType();

        clothingType.setName(clothingTypeValidationDTO.name());

        if (clothingTypeValidationDTO.image() != null) {
            String image = fileUpload.upload( clothingTypeValidationDTO.image(), "clothing-type");
            clothingType.setImage(image);
        }

        return new ResponseEntity<>( clothingTypeRepository.save(clothingType), HttpStatus.OK );
    }

    public ResponseEntity<?> updateClothingType (
            ClothingTypeValidationDTO clothingTypeValidationDTO,
            Long id
    ) {

        ClothingType updatedClothingType = clothingTypeRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("you can't update an not found clothing type"));

        updatedClothingType.setName(clothingTypeValidationDTO.name());
        if (clothingTypeValidationDTO.image() != null) {
            String image = fileUpload.upload( clothingTypeValidationDTO.image(), "clothing-type");
            updatedClothingType.setImage(image);
        }

        return new ResponseEntity<>( clothingTypeRepository.save( updatedClothingType ), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteClothingType ( Long id ) {
        ClothingType clothingType = clothingTypeRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("you can't update an not found clothing type"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", clothingType.getName() + " has been Deleted");
        response.put("id", clothingType.getId());

        clothingTypeRepository.deleteById( id );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}




















