package com.fiap.ecommerce.controllers;

import com.fiap.ecommerce.dtos.UserDto;
import com.fiap.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    private ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return  ResponseEntity.ok(userService.save(userDto));
    }
}
