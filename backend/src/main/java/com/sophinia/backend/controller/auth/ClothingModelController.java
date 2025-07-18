package com.sophinia.backend.controller.auth;


import com.sophinia.backend.dto.validation.ClothingModelValidationDTO;
import com.sophinia.backend.model.ClothingModel;
import com.sophinia.backend.service.ClothingModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clothing-model")
public class ClothingModelController {

    private final ClothingModelService clothingModelService;

    public ClothingModelController (
       final ClothingModelService clothingModelService
    ) {
        this.clothingModelService = clothingModelService;
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody ClothingModelValidationDTO clothingModelValidationDTO) {
        ClothingModel clothingModel = new ClothingModel();

        clothingModel.setName( clothingModelValidationDTO.name() );
        clothingModel.setImage( clothingModelValidationDTO.image() );

        return clothingModelService.createClothingModel( clothingModel );
    }

}
