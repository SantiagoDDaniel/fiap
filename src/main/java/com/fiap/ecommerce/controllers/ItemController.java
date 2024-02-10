package com.fiap.ecommerce.controllers;

import com.fiap.ecommerce.dtos.ItemDto;
import com.fiap.ecommerce.dtos.ItemResponseDto;
import com.fiap.ecommerce.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService service;

    @PostMapping("/create")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ItemResponseDto> save(@RequestBody @Validated ItemDto request) {
        return ResponseEntity.ok(service.save(request));
    }
}
