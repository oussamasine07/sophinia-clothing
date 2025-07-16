package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.MeasurementFieldValidationDTO;
import com.sophinia.backend.model.MeasurementField;
import com.sophinia.backend.service.MeasurementFieldService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/measurement-field")
public class MeasurementFieldController {

    private final MeasurementFieldService measurementFieldService;

    public MeasurementFieldController (
            final MeasurementFieldService measurementFieldService
    ) {
        this.measurementFieldService = measurementFieldService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show (@PathVariable Long id) {
        return measurementFieldService.getMeasurementFieldById(id);
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody MeasurementFieldValidationDTO measurementFieldValidationDTO) {
        MeasurementField measurementField = new MeasurementField();
        measurementField.setName( measurementFieldValidationDTO.name());

        return measurementFieldService.createMeasurementField( measurementField );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (
            @Valid @RequestBody MeasurementFieldValidationDTO measurementFieldValidationDTO,
            @PathVariable Long id
    ) {
        MeasurementField measurementField = new MeasurementField();
        measurementField.setName( measurementFieldValidationDTO.name());

        return measurementFieldService.updateMeasurementField( id, measurementField );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete ( @PathVariable Long id ) {

        return measurementFieldService.deleteMeasurementField( id );

    }

}






















