package com.sophinia.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.security.auth.Subject;

@Entity
@Table(name = "admins")
public class Admin extends User {}
