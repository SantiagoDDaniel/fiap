package com.fiap.ecommerce.user.model;

public enum RoleEnum {
    ADMIN("admin"),
    USER("user");

    private String role;

    private RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
