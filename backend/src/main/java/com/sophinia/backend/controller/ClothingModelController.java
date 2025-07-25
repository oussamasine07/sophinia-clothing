package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.ClothingModelValidationDTO;
import com.sophinia.backend.model.ClothingModel;
import com.sophinia.backend.service.ClothingModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    public ResponseEntity<?> index () {
        return clothingModelService.getAllClothingModels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show (@PathVariable Long id) {

        return clothingModelService.getClothingModel( id );

    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create (@RequestBody ClothingModelValidationDTO clothingModelValidationDTO) {
        ClothingModel clothingModel = new ClothingModel();

        clothingModel.setName( clothingModelValidationDTO.name() );
        clothingModel.setImage( clothingModelValidationDTO.image() );

        return clothingModelService.createClothingModel( clothingModel );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update (
            @RequestBody ClothingModelValidationDTO clothingModelValidationDTO,
            @PathVariable Long id
    ) {
        ClothingModel clothingModel = new ClothingModel();

        clothingModel.setName( clothingModelValidationDTO.name() );
        clothingModel.setImage( clothingModelValidationDTO.image() );

        return clothingModelService.updateClothingModel( clothingModel, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete ( @PathVariable Long id ) {
        return clothingModelService.deleteClothingModel( id );
    }

}


























