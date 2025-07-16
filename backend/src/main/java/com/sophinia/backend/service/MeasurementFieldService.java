package com.sophinia.backend.service;

import com.sophinia.backend.exception.NotFoundException;
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

    public ResponseEntity<?> updateMeasurementField ( Long id, MeasurementField measurementField ) {
        MeasurementField updatedMeasurementField = measurementFieldRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("this measurement not found"));

        updatedMeasurementField.setName( measurementField.getName() );

        return new ResponseEntity<>(measurementFieldRepository.save( updatedMeasurementField ), HttpStatus.OK);
    }

}
























