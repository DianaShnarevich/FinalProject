package com.example.fitness.config;

import com.example.fitness.dao.RecipeDao;
import com.example.fitness.service.RecipeService;
import com.example.fitness.service.api.IRecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecipeServiceConfig {

	@Bean
	public IRecipeService recipeService(RecipeDao recipeDao) {
		return new RecipeService(recipeDao);
	}
}
