package com.sophinia.backend.controller;


import com.sophinia.backend.dto.request.ProductValidationDTO;
import com.sophinia.backend.model.Product;
import com.sophinia.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController (
        final ProductService productService
    ) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> index () {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Product> create ( @Valid ProductValidationDTO productValidationDTO ) {
        
        return productService.createProduct( productValidationDTO );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> update (
            @Valid @ModelAttribute ProductValidationDTO productValidationDTO,
            @PathVariable Long id
    ) {

        return productService.updateProduct( productValidationDTO, id );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable Long id ) {
        return productService.deleteProduct( id );
    }

}






























