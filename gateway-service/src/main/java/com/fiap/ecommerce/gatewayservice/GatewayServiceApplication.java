package com.fiap.ecommerce.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/products/**")
						.uri("lb://product-service"))
				.route(r -> r.path("/carts/**")
						.uri("lb://cart-service"))
				.route(r -> r.path("/orders/**")
						.uri("lb://order-service"))
				.route(r -> r.path("/users/**")
						.uri("lb://user-service"))
				.build();
	}

}
