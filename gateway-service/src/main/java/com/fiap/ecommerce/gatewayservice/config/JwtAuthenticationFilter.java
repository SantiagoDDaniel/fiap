package com.fiap.ecommerce.gatewayservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.ws.rs.NotAuthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {


    @Value("${jwt.secret}")
    private String secretKey;
    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
        // Put configuration properties here

    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                try {
                    Claims claims = Jwts.parser()
                            .setSigningKey(secretKey.getBytes())
                            .parseClaimsJws(token)
                            .getBody();
                } catch (SignatureException | ExpiredJwtException e) {
                    throw new NotAuthorizedException("Token inválido ou expirado", HttpStatus.UNAUTHORIZED);
                }
            } else {
                throw new NotAuthorizedException("Token não encontrado", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        };
    }
}
