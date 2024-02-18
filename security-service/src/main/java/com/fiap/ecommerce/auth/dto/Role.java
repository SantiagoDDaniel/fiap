package com.fiap.ecommerce.auth.dto;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
