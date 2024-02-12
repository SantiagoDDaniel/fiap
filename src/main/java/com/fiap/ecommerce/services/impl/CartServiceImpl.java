package com.fiap.ecommerce.services.impl;

import com.fiap.ecommerce.models.Cart;
import com.fiap.ecommerce.models.CartItem;
import com.fiap.ecommerce.models.Item;
import com.fiap.ecommerce.models.Usuario;
import com.fiap.ecommerce.repositories.CartItemRepository;
import com.fiap.ecommerce.repositories.CartRepository;
import com.fiap.ecommerce.repositories.ItemRepository;
import com.fiap.ecommerce.repositories.UserRepository;
import com.fiap.ecommerce.services.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void addItemToCart(Long productId, int quantity) {

        Usuario user = userRepository.findById(getLoggedInUserId())
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o usuario com o ID: " + getLoggedInUserId()));
        Item product = itemRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + productId));

        Cart cart = cartRepository.findByUserId(getLoggedInUserId())
                .orElseGet(() -> createNewCart(user));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }
    }

    @Transactional
    @Override
    public void removeItemFromCart(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    private Cart createNewCart(Usuario user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        return cartRepository.save(newCart);
    }

    private Long getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            new RuntimeException("Usuário não autenticado");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            Usuario user = userRepository.findByname(((UserDetails) principal).getUsername());
            return user.getId();
        } else {
            throw new RuntimeException("Usuário não autenticado");
        }
    }
}
