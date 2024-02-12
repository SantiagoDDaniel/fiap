package com.fiap.ecommerce.controllers;

import com.fiap.ecommerce.dtos.AddItemRequest;
import com.fiap.ecommerce.services.CartService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public String addItemToCart(@RequestBody AddItemRequest request) {
        cartService.addItemToCart(request.productId(), request.quantity());
        return "Item adicionado no carrinho com sucesso";
    }

    @DeleteMapping("/remove/{itemId}")
    public String removeItemFromCart(@PathVariable Long itemId) {
        cartService.removeItemFromCart(itemId);
        return "Item removido no carrinho com sucesso";
    }

    @PostMapping("/pay")
    public String simulatePayment() {
        return "Payment simulated successfully";
    }


}
