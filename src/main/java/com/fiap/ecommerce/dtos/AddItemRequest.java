package com.fiap.ecommerce.dtos;

public record AddItemRequest(
        Long productId,
        int quantity
) {
}
