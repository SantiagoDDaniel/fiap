package com.fiap.ecommerce.cartservice.service;

import com.fiap.ecommerce.cartservice.model.Cart;
import com.fiap.ecommerce.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartService {
    private final CartRepository cartRepository;
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getLastCartByUserId(Long userId) {
        return cartRepository.findCartsByUserId(userId)
                .stream()
                .max(Comparator.comparing(Cart::getCreatedAt))
                .orElseThrow(() -> new NoSuchElementException("No cart found for user ID " + userId));
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long id, Cart cart) {
        Cart existingCart = getCartById(id);
        if (existingCart == null) {
            return null;
        }
        existingCart.setItems(cart.getItems());
        existingCart.setUpdatedAt(cart.getUpdatedAt());
        existingCart.setCreatedAt(cart.getCreatedAt());
        existingCart.setStatus(cart.getStatus());
        existingCart.setTotalPrice(cart.getTotalPrice());
        return cartRepository.save(existingCart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
