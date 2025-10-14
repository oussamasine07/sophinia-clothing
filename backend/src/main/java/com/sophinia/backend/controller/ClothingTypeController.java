package com.sophinia.backend.controller;


import com.sophinia.backend.dto.request.ClothingTypeValidationDTO;
import com.sophinia.backend.model.ClothingType;
import com.sophinia.backend.service.ClothingTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<ClothingType>> index () {
        return clothingTypeService.getAllClothingTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingType> show (@PathVariable Long id ) {
        return clothingTypeService.getClothingTypeById( id );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClothingType> create (@Valid ClothingTypeValidationDTO clothingTypeValidationDTO) {
        return clothingTypeService.createClothingType( clothingTypeValidationDTO );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ClothingType> update (
            @Valid ClothingTypeValidationDTO clothingTypeValidationDTO,
            @PathVariable Long id
    ) {

        return clothingTypeService.updateClothingType( clothingTypeValidationDTO, id );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable Long id ) {
        return clothingTypeService.deleteClothingType( id );
    }


}

























