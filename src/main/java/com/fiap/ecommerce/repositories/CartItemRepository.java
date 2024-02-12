package com.fiap.ecommerce.repositories;

import com.fiap.ecommerce.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
