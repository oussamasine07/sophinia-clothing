package com.sophinia.backend.service;


import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.Product;
import com.sophinia.backend.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService (
            final ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<?> getAllProducts () {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<?> createProduct (Product product) {
        return new ResponseEntity<>( productRepository.save(product), HttpStatus.OK );
    }


    public ResponseEntity<?> updateProduct (Product product, Long id) {
        Product updatedProduct = productRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this product not found"));

        updatedProduct.setName( product.getName() );
        updatedProduct.setDescription( product.getDescription() );
        updatedProduct.setClothingType( product.getClothingType() );
        updatedProduct.setProductMeasurementFields( product.getProductMeasurementFields() );

        return new ResponseEntity<>( productRepository.save( updatedProduct ), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteProduct ( Long id ) {

        Product deletedProduct = productRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this product not found"));

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "decoration " + deletedProduct.getName() + " removed");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
































