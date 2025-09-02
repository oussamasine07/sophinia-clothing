package com.sophinia.backend.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = true)
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ManyToOne
    private ClothingType clothingType;

    @ManyToMany
    @JoinTable(
            name = "product_measurement_fields",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "measurement_field_id")
    )
    private List<MeasurementField> productMeasurementFields;

    @OneToMany(mappedBy = "product")
    private List<MeasurementSet> measurementSets;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClothingType getClothingType() {
        return clothingType;
    }

    public void setClothingType(ClothingType clothingType) {
        this.clothingType = clothingType;
    }

    public List<MeasurementField> getProductMeasurementFields() {
        return productMeasurementFields;
    }

    public void setProductMeasurementFields(List<MeasurementField> productMeasurementFields) {
        this.productMeasurementFields = productMeasurementFields;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<MeasurementSet> getMeasurementSets() {
        return measurementSets;
    }

    public void setMeasurementSets(List<MeasurementSet> measurementSets) {
        this.measurementSets = measurementSets;
    }


}


























