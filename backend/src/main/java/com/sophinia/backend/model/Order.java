package com.sophinia.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    private OrderStatus orderStatuse;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Decoration decoration;

    @JsonIgnore
    @ManyToOne
    private Fabric fabric;

    @JsonIgnore
    @ManyToOne
    private ClothingModel clothingModel;

    @JsonIgnore
    @ManyToOne
    private Design design;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<MeasurementSet> measurementSets;

    @JsonIgnore
    @ManyToOne
    private Client client;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avialability_id", referencedColumnName = "id")
    private Availability avialability;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMeasurementSets(List<MeasurementSet> measurementSets) {
        this.measurementSets = measurementSets;
    }

    public Availability getAvialability() {
        return avialability;
    }

    public void setAvialability(Availability avialability) {
        this.avialability = avialability;
    }
}
