package com.fiap.ecommerce.gatewayservice.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/api-docs/swagger-docs",
            "/eureka"
    );
    public static final List<String> openApiEndpointsAdmin = List.of(
            "/auth/delete"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));


    public Predicate<ServerHttpRequest> isAdminRoute =
            request -> openApiEndpointsAdmin
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));
}
