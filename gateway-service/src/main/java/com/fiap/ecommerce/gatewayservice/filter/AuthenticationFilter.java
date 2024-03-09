package com.fiap.ecommerce.gatewayservice.filter;

import com.fiap.ecommerce.gatewayservice.config.AuthenticationException;
import com.fiap.ecommerce.gatewayservice.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (validator.isSecured.test(exchange.getRequest())) {
            String token = extractAndValidateToken(exchange.getRequest());

            if (token == null || token.isEmpty()) {
                throw new AuthenticationException("Invalid or missing token");
            }
            try { jwtUtil.validateToken(token);
                String userRole = jwtUtil.getUserRoleFromToken(token);

                if (validator.isAdminRoute.test(request) && !userRole.equals("ROLE_ADMIN")) {
                    throw new AuthenticationException("Unauthorized access to application");
                }
            } catch (Exception e) {
                System.out.println("Invalid access...!");
                throw new AuthenticationException("Unauthorized access to application");
            }
        }
        return chain.filter(exchange);
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

    @Override
    public int getOrder() {
        return -100;
    }

}