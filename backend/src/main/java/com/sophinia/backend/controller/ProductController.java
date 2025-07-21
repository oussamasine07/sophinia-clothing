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

        product.setProductMeasurementFields( measurementFields );

        return productService.createProduct( product );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (
            @Valid @RequestBody ProductValidationDTO productValidationDTO,
            @PathVariable Long id
    ) {
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

        product.setProductMeasurementFields( measurementFields );

        return productService.updateProduct( product, id );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete ( @PathVariable Long id ) {
        return productService.deleteProduct( id );
    }

}






























