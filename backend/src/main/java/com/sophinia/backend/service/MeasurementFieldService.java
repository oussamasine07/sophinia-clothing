package com.sophinia.backend.service;

import com.sophinia.backend.model.MeasurementField;
import com.sophinia.backend.repository.MeasurementFieldRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MeasurementFieldService {

    private final MeasurementFieldRepository measurementFieldRepository;

    public MeasurementFieldService (
            final MeasurementFieldRepository measurementFieldRepository
    ) {
        this.measurementFieldRepository = measurementFieldRepository;
    }

    public ResponseEntity<?> createMeasurementField (MeasurementField measurementField) {
        MeasurementField savedMeasurmentField = measurementFieldRepository.save( measurementField );
        return new ResponseEntity<>(savedMeasurmentField, HttpStatus.OK);

    }

}
