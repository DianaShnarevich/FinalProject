package com.example.fitness;

import com.example.fitness.config.properties.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({JWTProperty.class})
@SpringBootApplication
public class FitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessApplication.class, args);
	}

}