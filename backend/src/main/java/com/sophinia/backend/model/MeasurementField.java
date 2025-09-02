package com.sophinia.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "measurement_fields")
public class MeasurementField {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "productMeasurementFields")
    private List<Product> products;

    @OneToMany(mappedBy = "measurementField")
    private List<MeasurementValue> measurementValues;

    public MeasurementField () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<MeasurementValue> getMeasurementValues() {
        return measurementValues;
    }

    public void setMeasurementValues(List<MeasurementValue> measurementValues) {
        this.measurementValues = measurementValues;
    }
}
