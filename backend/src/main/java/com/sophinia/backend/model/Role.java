package com.sophinia.backend.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static com.sophinia.backend.model.Permission.*;

public enum Role {


    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    CLIENT_READ,
                    CLIENT_CREATE,
                    CLIENT_UPDATE,
                    CLIENT_DELETE,
                    EMPLOYEE_READ,
                    EMPLOYEE_CREATE,
                    EMPLOYEE_UPDATE,
                    EMPLOYEE_DELETE
            )
    ),
    CLIENT(
            Set.of(
                    CLIENT_READ,
                    CLIENT_CREATE,
                    CLIENT_UPDATE,
                    CLIENT_DELETE
            )
    ),
    EMPLOYEE(
            Set.of(
                    EMPLOYEE_READ,
                    EMPLOYEE_CREATE,
                    EMPLOYEE_UPDATE,
                    EMPLOYEE_DELETE
            )
    );

    private final Set<Permission> permissions;

    Role (
            final Set<Permission> permissions
    ) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities () {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(this.name()) )
                .toList();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }




}
