package com.sophinia.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "measurement_values")
public class MeasurementValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private double value;


    @ManyToOne
    private MeasurementSet measurementSet;

    @ManyToOne
    private MeasurementField measurementField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public MeasurementSet getMeasurementSet() {
        return measurementSet;
    }

    public void setMeasurementSet(MeasurementSet measurementSet) {
        this.measurementSet = measurementSet;
    }

    public MeasurementField getMeasurementField() {
        return measurementField;
    }

    public void setMeasurementField(MeasurementField measurementField) {
        this.measurementField = measurementField;
    }
}
