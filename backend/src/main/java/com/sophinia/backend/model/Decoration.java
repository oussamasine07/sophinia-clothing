package com.sophinia.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "decorations")
public class Decoration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

}
