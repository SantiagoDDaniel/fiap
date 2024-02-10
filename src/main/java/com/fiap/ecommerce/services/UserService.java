package com.fiap.ecommerce.services;

import com.fiap.ecommerce.dtos.UserDto;

public interface UserService {
    UserDto save(UserDto request);
}
