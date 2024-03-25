package com.fiap.ecommerce.cartservice.repository;


import com.fiap.ecommerce.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart  c WHERE c.idUser = ?1 ORDER BY c.createdAt DESC")
    List<Cart> findCartsByUserId(Long userId);
}
