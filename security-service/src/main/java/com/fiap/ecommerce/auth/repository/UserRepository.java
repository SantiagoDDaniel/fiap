package com.fiap.ecommerce.auth.repository;

import com.fiap.ecommerce.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}