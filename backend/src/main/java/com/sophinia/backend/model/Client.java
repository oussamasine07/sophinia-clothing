package com.sophinia.backend.model;

import jakarta.persistence.*;

import javax.security.auth.Subject;

@Entity
@Table(name = "clients")
public class Client extends User {

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "cnss_code")
    private String cnssCode;

}
