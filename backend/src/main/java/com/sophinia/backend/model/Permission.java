package com.sophinia.backend.model;

public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    CLIENT_READ("client:read"),
    CLIENT_CREATE("client:create"),
    CLIENT_UPDATE("client:update"),
    CLIENT_DELETE("client:delete"),

    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_CREATE("employee:create"),
    EMPLOYEE_UPDATE("employee:update"),
    EMPLOYEE_DELETE("employee:delete");

    private final String permission;

    Permission(final String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
