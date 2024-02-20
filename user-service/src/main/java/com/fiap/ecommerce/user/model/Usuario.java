package com.fiap.ecommerce.user.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "TB_USUARIO"
)
public class Usuario{
    @Id
    @GeneratedValue
    private Long id;
    @Column(
            nullable = false
    )
    private String name;
    @Column(
            nullable = false
    )
    private String login;
    @Column(
            nullable = false
    )
    private String password;
    @Column(
            nullable = false
    )
    private RoleEnum role;

    public Usuario(String name, String login, String senha, RoleEnum role) {
        this.name = name;
        this.login = login;
        this.password = senha;
        this.role = role;
    }


    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public RoleEnum getRole() {
        return this.role;
    }

    public Usuario() {
    }
}