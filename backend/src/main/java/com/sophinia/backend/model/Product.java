package com.sophinia.backend.model;


import jakarta.persistence.*;

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

    @ManyToOne
    private ClothingType clothingType;



    @ManyToOne
    private Fabric fabric;

    @ManyToOne
    private Decoration decoration;

    // todo: add model relationship


    


}
