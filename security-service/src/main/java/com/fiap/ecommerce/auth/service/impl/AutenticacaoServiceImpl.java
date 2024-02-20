package com.fiap.ecommerce.auth.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fiap.ecommerce.auth.dto.AuthDto;
import com.fiap.ecommerce.auth.model.Usuario;
import com.fiap.ecommerce.auth.repository.UserRepository;
import com.fiap.ecommerce.auth.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.name.jwt}")
    private String applicationName;

    public AutenticacaoServiceImpl() {
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = this.userRepository.findByLogin(login);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com login: " + login);
        } else {
            return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
        }
    }

    public String getToken(AuthDto authDto) {
        Usuario usuario = this.userRepository.findByLogin(authDto.login());
        return this.geraTokenJwt(usuario);
    }

    public String geraTokenJwt(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.create().withIssuer(this.applicationName).withSubject(usuario.getUsername()).withExpiresAt(this.geraDataExpiracao()).sign(algorithm);
        } catch (JWTCreationException var3) {
            throw new RuntimeException("Erro ao tentar gerar o token! " + var3.getMessage());
        }
    }

    public String validateTokenJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.require(algorithm).withIssuer(this.applicationName).build().verify(token).getSubject();
        } catch (JWTVerificationException var3) {
            return "";
        }
    }

    private Instant geraDataExpiracao() {
        return LocalDateTime.now().plusHours(8L).toInstant(ZoneOffset.of("-03:00"));
    }
}
