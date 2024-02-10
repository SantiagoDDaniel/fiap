package com.fiap.ecommerce.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fiap.ecommerce.dtos.AuthDto;
import com.fiap.ecommerce.models.Usuario;
import com.fiap.ecommerce.repositories.UserRepository;
import com.fiap.ecommerce.services.AutenticacaoService;
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

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByLogin(login);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com login: " + login);
        }
        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
    }

    @Override
    public String getToken(AuthDto authDto) {
        Usuario usuario = userRepository.findByLogin(authDto.login());
        return geraTokenJwt(usuario);
    }

    public String geraTokenJwt(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.create()
                    .withIssuer(applicationName)
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(geraDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar o token! " + exception.getMessage());
        }
    }

    public String validateTokenJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.require(algorithm)
                    .withIssuer(applicationName)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant geraDataExpiracao() {
        return LocalDateTime.now()
                .plusHours(8)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
