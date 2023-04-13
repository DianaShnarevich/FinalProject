package com.example.fitness.config;

import com.example.fitness.dao.ProductDao;
import com.example.fitness.service.ProductService;
import com.example.fitness.service.api.IProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

	@Bean
	public IProductService productService(ProductDao productDao){
		return new ProductService(productDao);
	}
}
