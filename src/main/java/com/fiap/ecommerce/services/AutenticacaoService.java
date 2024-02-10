package com.fiap.ecommerce.services;

import com.fiap.ecommerce.dtos.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {
    public String getToken(AuthDto authDto);
    public String validateTokenJwt(String token);
}
