package com.fiap.ecommerce.auth.controller;

import com.fiap.ecommerce.auth.dto.AuthDto;
import com.fiap.ecommerce.auth.dto.JwtRequest;
import com.fiap.ecommerce.auth.service.AutenticacaoService;
import com.fiap.ecommerce.auth.service.impl.AutenticacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/auth"})
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AutenticacaoService autenticacaoService;

    public AuthenticationController() {
    }

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());
        this.authenticationManager.authenticate(usuarioAutenticationToken);
        return ResponseEntity.ok(this.autenticacaoService.getToken(authDto));
    }
}