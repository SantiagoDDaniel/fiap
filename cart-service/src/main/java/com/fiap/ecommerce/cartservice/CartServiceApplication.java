package com.fiap.ecommerce.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.support.StandardServletEnvironment;

@SpringBootApplication
@EnableDiscoveryClient
public class CartServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CartServiceApplication.class);
		if(new StandardServletEnvironment().getActiveProfiles().length == 0) {
			app.setAdditionalProfiles("dev");
		}

		ConfigurableApplicationContext context = app.run(args);
	}

}
