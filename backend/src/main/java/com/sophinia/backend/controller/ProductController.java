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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @PostMapping
    public ResponseEntity<?> create ( @Valid @RequestBody ProductValidationDTO productValidationDTO) {

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
                .toList();

        productValidationDTO.measurement_fields()
                .forEach(measure -> {
                    MeasurementField newMeasure = new MeasurementField();
                    newMeasure.setName( measure.name() );


                    MeasurementField savedMeasure = (MeasurementField) measurementFieldService
                            .createMeasurementField( newMeasure )
                            .getBody();

                    measurementFields.add( savedMeasure );
                });

        product.setProductMeasurementFields( measurementFields );

        return productService.createProduct( product );
    }

}






























