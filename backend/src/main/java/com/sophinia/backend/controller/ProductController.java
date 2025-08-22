package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.ProductValidationDTO;
import com.sophinia.backend.model.ClothingType;
import com.sophinia.backend.model.MeasurementField;
import com.sophinia.backend.model.Product;
import com.sophinia.backend.service.ClothingTypeService;
import com.sophinia.backend.service.MeasurementFieldService;
import com.sophinia.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ClothingTypeService clothingTypeService;
    private final MeasurementFieldService measurementFieldService;

    public ProductController (
        final ProductService productService,
        final ClothingTypeService clothingTypeService,
        final MeasurementFieldService measurementFieldService
    ) {
        this.productService = productService;
        this.clothingTypeService = clothingTypeService;
        this.measurementFieldService = measurementFieldService;
    }

    @GetMapping
    public ResponseEntity<?> index () {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create ( @Valid ProductValidationDTO productValidationDTO ) {
        
        return productService.createProduct( productValidationDTO );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update (
            @Valid @ModelAttribute ProductValidationDTO productValidationDTO,
            @PathVariable Long id
    ) {

        return productService.updateProduct( productValidationDTO, id );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete ( @PathVariable Long id ) {
        return productService.deleteProduct( id );
    }

}






























