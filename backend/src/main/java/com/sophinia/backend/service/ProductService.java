package com.sophinia.backend.service;


import com.sophinia.backend.model.Product;
import com.sophinia.backend.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService (
            final ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }


    public ResponseEntity<?> createProduct (Product product) {
        return new ResponseEntity<>( productRepository.save(product), HttpStatus.OK );
    }

}
































