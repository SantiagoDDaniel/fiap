package com.fiap.ecommerce.user.dto;


import com.fiap.ecommerce.user.model.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto (
        @NotBlank(message = "Nome  Obrigatorio") String name,
        @NotBlank(message = "Login Obrigatorio") @Email(message = "Login should be an email") String login,
        @NotBlank(message = "Senha Obrigatoria") @Size(min = 6, message = "Senha deve ter no minino 6 caracteres") String password,
        @NotNull(message = "Role Obrigatoria") RoleEnum role) {
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
