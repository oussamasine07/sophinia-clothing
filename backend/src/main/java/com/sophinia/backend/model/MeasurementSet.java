package com.sophinia.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "measuremet_sets")
public class MeasurementSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    @OneToMany(mappedBy = "measurementSet")
    private List<MeasurementValue> measurementValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<MeasurementValue> getMeasurementValues() {
        return measurementValues;
    }

    public void setMeasurementValues(List<MeasurementValue> measurementValues) {
        this.measurementValues = measurementValues;
    }
}
