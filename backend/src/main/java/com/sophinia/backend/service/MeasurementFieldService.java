package com.sophinia.backend.service;

import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.MeasurementField;
import com.sophinia.backend.repository.MeasurementFieldRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementFieldService {

    private static final String CLOTHING_FIELD_NOT_FOUND = "this measurement not found";

    private final MeasurementFieldRepository measurementFieldRepository;

    public MeasurementFieldService (
            final MeasurementFieldRepository measurementFieldRepository
    ) {
        this.measurementFieldRepository = measurementFieldRepository;
    }

    public ResponseEntity<List<MeasurementField>> getMEasurementFields () {
        List<MeasurementField> measurementFields = measurementFieldRepository.findAll();

        return new ResponseEntity<>(measurementFields, HttpStatus.OK);
    }

    public ResponseEntity<MeasurementField> getMeasurementFieldById ( Long id ) {
        MeasurementField measurementField = measurementFieldRepository.findById( id )
                .orElseThrow(() -> new NotFoundException(CLOTHING_FIELD_NOT_FOUND));

        return new ResponseEntity<>(measurementField, HttpStatus.OK);
    }

    public ResponseEntity<MeasurementField> createMeasurementField (MeasurementField measurementField) {
        MeasurementField savedMeasurmentField = measurementFieldRepository.save( measurementField );
        return new ResponseEntity<>(savedMeasurmentField, HttpStatus.OK);

    }

    public ResponseEntity<MeasurementField> updateMeasurementField ( Long id, MeasurementField measurementField ) {
        MeasurementField updatedMeasurementField = measurementFieldRepository.findById( id )
                .orElseThrow(() -> new NotFoundException(CLOTHING_FIELD_NOT_FOUND));

        updatedMeasurementField.setName( measurementField.getName() );

        return new ResponseEntity<>(measurementFieldRepository.save( updatedMeasurementField ), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteMeasurementField ( Long id ) {
        MeasurementField measurementField = measurementFieldRepository.findById( id )
                .orElseThrow(() -> new NotFoundException(CLOTHING_FIELD_NOT_FOUND));

        measurementFieldRepository.deleteById( id );

        return new ResponseEntity<>( measurementField.getName(), HttpStatus.OK);
    }

}
























