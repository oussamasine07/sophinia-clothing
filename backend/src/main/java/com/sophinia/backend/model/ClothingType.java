package com.sophinia.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clothing_types")
public class ClothingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image", nullable = true)
    private String image;

    @OneToMany(mappedBy = "clothingType")
    private List<Product> products;

    public ClothingType () {}

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}






















