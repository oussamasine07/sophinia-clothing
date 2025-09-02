package com.sophinia.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table( name = "orders" )
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @ManyToOne
    private OrderStatus orderStatuse;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Decoration decoration;

    @ManyToOne
    private Fabric fabric;

    @ManyToOne
    private ClothingModel clothingModel;

    @ManyToOne
    private Design design;

    @OneToMany(mappedBy = "order")
    private List<MeasurementSet> measurementSets;

    @ManyToOne
    private Client client;

    public Order () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getOrderStatuse() {
        return orderStatuse;
    }

    public void setOrderStatuse(OrderStatus orderStatuse) {
        this.orderStatuse = orderStatuse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Decoration getDecoration() {
        return decoration;
    }

    public void setDecoration(Decoration decoration) {
        this.decoration = decoration;
    }

    public Fabric getFabric() {
        return fabric;
    }

    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    public ClothingModel getClothingModel() {
        return clothingModel;
    }

    public void setClothingModel(ClothingModel clothingModel) {
        this.clothingModel = clothingModel;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public List<MeasurementSet> getMeasurementSets() {
        return measurementSets;
    }

    public void setMeasurementSets(List<MeasurementSet> measurementSets) {
        this.measurementSets = measurementSets;
    }
}
