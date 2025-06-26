package com.sophinia.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fabrics")
public class Fabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

}
