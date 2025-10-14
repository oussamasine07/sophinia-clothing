package com.sophinia.backend.controller;


import com.sophinia.backend.dto.request.ClothingModelValidationDTO;
import com.sophinia.backend.model.ClothingModel;
import com.sophinia.backend.service.ClothingModelService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<ClothingModel>> index () {
        return clothingModelService.getAllClothingModels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingModel> show (@PathVariable Long id) {

        return clothingModelService.getClothingModel( id );

    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClothingModel> create (@Valid ClothingModelValidationDTO clothingModelValidationDTO) {

        return clothingModelService.createClothingModel( clothingModelValidationDTO );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ClothingModel> update (
            @Valid ClothingModelValidationDTO clothingModelValidationDTO,
            @PathVariable Long id
    ) {

        return clothingModelService.updateClothingModel( clothingModelValidationDTO, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable Long id ) {
        return clothingModelService.deleteClothingModel( id );
    }

}


























