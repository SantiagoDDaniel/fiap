package com.fiap.ecommerce.controllers;

import com.fiap.ecommerce.dtos.AuthDto;
import com.fiap.ecommerce.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody AuthDto authDto) {
        var usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());
        authenticationManager.authenticate(usuarioAutenticationToken);
        return ResponseEntity.ok(autenticacaoService.getToken(authDto));
    }
}
