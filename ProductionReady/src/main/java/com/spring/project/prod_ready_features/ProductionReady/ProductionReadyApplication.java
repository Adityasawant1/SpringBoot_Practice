package com.spring.project.prod_ready_features.ProductionReady;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ProductionReadyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductionReadyApplication.class, args);
	}

}
