package com.fiap.ecommerce.gatewayservice.config;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}
