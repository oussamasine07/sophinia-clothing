package com.sophinia.backend.controller;


import com.sophinia.backend.dto.request.MeasurementFieldValidationDTO;
import com.sophinia.backend.model.MeasurementField;
import com.sophinia.backend.service.MeasurementFieldService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/measurement-field")
public class MeasurementFieldController {

    private final MeasurementFieldService measurementFieldService;

    public MeasurementFieldController (
            final MeasurementFieldService measurementFieldService
    ) {
        this.measurementFieldService = measurementFieldService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<MeasurementField>> index () {
        return measurementFieldService.getMEasurementFields();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MeasurementField> show (@PathVariable Long id) {
        return measurementFieldService.getMeasurementFieldById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MeasurementField> create (@Valid @RequestBody MeasurementFieldValidationDTO measurementFieldValidationDTO) {
        MeasurementField measurementField = new MeasurementField();
        measurementField.setName( measurementFieldValidationDTO.name());

        return measurementFieldService.createMeasurementField( measurementField );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MeasurementField> update (
            @Valid @RequestBody MeasurementFieldValidationDTO measurementFieldValidationDTO,
            @PathVariable Long id
    ) {
        MeasurementField measurementField = new MeasurementField();
        measurementField.setName( measurementFieldValidationDTO.name());

        return measurementFieldService.updateMeasurementField( id, measurementField );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete ( @PathVariable Long id ) {

        return measurementFieldService.deleteMeasurementField( id );

    }

}






















