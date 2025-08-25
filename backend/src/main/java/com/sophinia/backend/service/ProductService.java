package com.sophinia.backend.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.mappingDTO.MeasurementFieldDTO;
import com.sophinia.backend.dto.validation.ProductValidationDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.ClothingType;
import com.sophinia.backend.model.MeasurementField;
import com.sophinia.backend.model.Product;
import com.sophinia.backend.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ClothingTypeService clothingTypeService;
    private final MeasurementFieldService measurementFieldService;
    private final FileUpload fileUpload;

    public ProductService (
            final ProductRepository productRepository,
            final ClothingTypeService clothingTypeService,
            final MeasurementFieldService measurementFieldService,
            final FileUpload fileUpload
    ) {
        this.productRepository = productRepository;
        this.clothingTypeService = clothingTypeService;
        this.measurementFieldService = measurementFieldService;
        this.fileUpload = fileUpload;
    }

    public ResponseEntity<?> getAllProducts () {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<?> getProductById ( Long id ) {
        Product product = productRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this product not found"));

        return new ResponseEntity<>( product, HttpStatus.OK );
    }

    public ResponseEntity<?> createProduct ( ProductValidationDTO productValidationDTO ) {

        Product product = new Product();

        product.setName(productValidationDTO.name());
        product.setDescription(productValidationDTO.description());

        // get clothing type
        ClothingType clothingType = (ClothingType) clothingTypeService
                .getClothingTypeById( productValidationDTO.clothing_type() )
                .getBody();
        product.setClothingType( clothingType );


        // setup measurements
        List<MeasurementField> measurementFields = productValidationDTO.measurements_fields_ids() == null
                ? new ArrayList<>()
                :  productValidationDTO
                .measurements_fields_ids()
                .stream()
                .map(id -> {
                    return (MeasurementField) measurementFieldService
                            .getMeasurementFieldById( id )
                            .getBody();
                })
                .collect(Collectors.toCollection(ArrayList::new));

        if (productValidationDTO.measurement_fields() != null) {
            productValidationDTO.measurement_fields()
                    .forEach(measure -> {
                        MeasurementField newMeasure = new MeasurementField();
                        newMeasure.setName( measure.name() );

                        MeasurementField savedMeasure = (MeasurementField) measurementFieldService
                                .createMeasurementField( newMeasure )
                                .getBody();

                        measurementFields.add( savedMeasure );
                    });
        }

        if (productValidationDTO.image() != null) {
            String image = fileUpload.upload( productValidationDTO.image(), "products");
            product.setImage(image);
        }


        product.setProductMeasurementFields( measurementFields );

        return new ResponseEntity<>( productRepository.save(product), HttpStatus.OK );
    }


    public ResponseEntity<?> updateProduct (ProductValidationDTO productValidationDTO, Long id) {
        Product updatedProduct = productRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this product not found"));

        updatedProduct.setName(productValidationDTO.name());
        updatedProduct.setDescription(productValidationDTO.description());

        // get clothing type
        ClothingType clothingType = (ClothingType) clothingTypeService
                .getClothingTypeById( productValidationDTO.clothing_type() )
                .getBody();
        updatedProduct.setClothingType( clothingType );

        // setup measurements
        List<MeasurementField> measurementFields = productValidationDTO.measurements_fields_ids() == null
                ? new ArrayList<>()
                :  productValidationDTO
                .measurements_fields_ids()
                .stream()
                .map(mId -> {
                    return (MeasurementField) measurementFieldService
                            .getMeasurementFieldById( mId )
                            .getBody();
                })
                .collect(Collectors.toCollection(ArrayList::new));

        if (productValidationDTO.measurement_fields() != null) {
            productValidationDTO.measurement_fields()
                    .forEach(measure -> {
                        MeasurementField newMeasure = new MeasurementField();
                        newMeasure.setName( measure.name() );

                        MeasurementField savedMeasure = (MeasurementField) measurementFieldService
                                .createMeasurementField( newMeasure )
                                .getBody();

                        measurementFields.add( savedMeasure );
                    });
        }

        if (productValidationDTO.image() != null) {
            String image = fileUpload.upload( productValidationDTO.image(), "products");
            updatedProduct.setImage(image);
        }

        updatedProduct.setProductMeasurementFields( measurementFields );

        return new ResponseEntity<>( productRepository.save( updatedProduct ), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteProduct ( Long id ) {

        Product deletedProduct = productRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this product not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "decoration " + deletedProduct.getName() + " removed");
        response.put("id", deletedProduct.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
































