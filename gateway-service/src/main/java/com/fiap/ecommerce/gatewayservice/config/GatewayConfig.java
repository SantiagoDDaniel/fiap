package com.fiap.ecommerce.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/products/**")
                        .uri("lb://product-service"))
                .route(r -> r.path("/carts/**")
                        .uri("lb://cart-service"))
                .route(r -> r.path("/orders/**")
                        .uri("lb://order-service"))
                .route(r -> r.path("/auth/**")
                        .uri("lb://security-service"))
                .build();
    }
}