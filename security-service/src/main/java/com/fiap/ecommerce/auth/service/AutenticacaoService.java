package com.fiap.ecommerce.auth.service;

import com.fiap.ecommerce.auth.dto.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {
    String getToken(AuthDto authDto);

    String validateTokenJwt(String token);
}