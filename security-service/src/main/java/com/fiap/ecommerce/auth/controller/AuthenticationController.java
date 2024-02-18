package com.fiap.ecommerce.auth.controller;

import com.fiap.ecommerce.auth.config.JwtResponse;
import com.fiap.ecommerce.auth.config.JwtTokenUtil;
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
    private AutenticacaoServiceImpl autenticacaoService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationController() {
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = autenticacaoService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}