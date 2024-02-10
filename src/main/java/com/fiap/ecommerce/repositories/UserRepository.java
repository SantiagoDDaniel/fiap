package com.fiap.ecommerce.repositories;

import com.fiap.ecommerce.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);
}