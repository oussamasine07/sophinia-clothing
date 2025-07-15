package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.MeasurementFieldValidationDTO;
import com.sophinia.backend.model.MeasurementField;
import com.sophinia.backend.service.MeasurementFieldService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/measurement-field")
public class MeasurementFieldController {

    private final MeasurementFieldService measurementFieldService;

    public MeasurementFieldController (
            final MeasurementFieldService measurementFieldService
    ) {
        this.measurementFieldService = measurementFieldService;
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody MeasurementFieldValidationDTO measurementFieldValidationDTO) {
        MeasurementField measurementField = new MeasurementField();
        measurementField.setName( measurementFieldValidationDTO.name());

        return measurementFieldService.createMeasurementField( measurementField );
    }

}
