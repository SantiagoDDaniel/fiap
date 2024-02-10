package com.fiap.ecommerce.dtos;

import com.fiap.ecommerce.enums.RoleEnum;

public record UserDto(
        String name,
        String login,
        String password,
        RoleEnum role

) {
}
