package com.fiap.ecommerce.services;

public interface CartService {
    void addItemToCart( Long productId, int quantity);

    void removeItemFromCart(Long itemId);
}
