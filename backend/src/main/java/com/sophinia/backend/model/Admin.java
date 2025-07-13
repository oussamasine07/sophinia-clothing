package com.sophinia.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.security.auth.Subject;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean implies(Subject subject) {
        return super.implies(subject);
    }
}
