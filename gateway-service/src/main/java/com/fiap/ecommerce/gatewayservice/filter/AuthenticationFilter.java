package com.fiap.ecommerce.gatewayservice.filter;

import com.fiap.ecommerce.gatewayservice.config.AuthenticationException;
import com.fiap.ecommerce.gatewayservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                String token = extractAndValidateToken((ServerHttpRequest) exchange.getRequest());
                jwtUtil.validateToken(token);
            }
            return chain.filter(exchange);
        });
    }

    private String extractAndValidateToken(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new AuthenticationException("Missing authorization header");
        }

        String token = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        if (token != null && token.startsWith(BEARER_PREFIX)) {
            token = token.substring(BEARER_PREFIX.length());
        }

        try {
            jwtUtil.validateToken(token);
        } catch (Exception e) {
            System.out.println("Invalid access...!");
            throw new AuthenticationException("Unauthorized access to application");
        }

        return token;
    }

    public static class Config {

    }
}
