package com.sophinia.backend.dto.mappingdto;

public class MeasurementValuesDTO {
    private String measurementField;
    private Double measurementValue;

    public MeasurementValuesDTO(String measurementField, Double measurementValue) {
        this.measurementField = measurementField;
        this.measurementValue = measurementValue;
    }

    public String getMeasurementField() {
        return measurementField;
    }

    public void setMeasurementField(String measurementField) {
        this.measurementField = measurementField;
    }

    public Double getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(Double measurementValue) {
        this.measurementValue = measurementValue;
    }
}
