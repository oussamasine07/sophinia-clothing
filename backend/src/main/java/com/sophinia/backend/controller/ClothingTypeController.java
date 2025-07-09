package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.ClothingTypeValidationDTO;
import com.sophinia.backend.model.ClothingType;
import com.sophinia.backend.service.ClothingTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clothing-type")
public class ClothingTypeController {

    private final ClothingTypeService clothingTypeService;

    public ClothingTypeController (
            final ClothingTypeService clothingTypeService
    ) {
        this.clothingTypeService = clothingTypeService;
    }

    @GetMapping
    public ResponseEntity<?> index () {
        return clothingTypeService.getAllClothingTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show (@PathVariable Long id ) {
        return clothingTypeService.getClothingTypeById( id );
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody ClothingTypeValidationDTO clothingTypeValidationDTO) {
        ClothingType clothingType = new ClothingType();

        clothingType.setName(clothingTypeValidationDTO.name());
        clothingType.setImage(clothingTypeValidationDTO.image());

        return clothingTypeService.createClothingType( clothingType );

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (
            @Valid @RequestBody ClothingTypeValidationDTO clothingTypeValidationDTO,
            @PathVariable Long id
    ) {
        ClothingType clothingType = new ClothingType();

        clothingType.setName(clothingTypeValidationDTO.name());
        clothingType.setImage(clothingTypeValidationDTO.image());

        return clothingTypeService.updateClothingType( clothingType, id );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete ( @PathVariable Long id ) {
        return clothingTypeService.deleteClothingType( id );
    }


}

























