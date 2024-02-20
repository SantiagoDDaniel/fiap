package com.fiap.ecommerce.user.dto;


import com.fiap.ecommerce.user.model.RoleEnum;

public record UserDto(String name, String login, String password, RoleEnum role) {
    public UserDto(String name, String login, String password, RoleEnum role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String name() {
        return this.name;
    }

    public String login() {
        return this.login;
    }

    public String password() {
        return this.password;
    }

    public RoleEnum role() {
        return this.role;
    }
}