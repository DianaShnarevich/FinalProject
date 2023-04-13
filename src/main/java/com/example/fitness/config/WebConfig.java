package com.example.fitness.config;

import com.example.fitness.service.api.IProductService;
import com.example.fitness.util.converters.LongToLocalDateTime;
import com.example.fitness.util.converters.composition.CompositionEntityToCompositionDTO;
import com.example.fitness.util.converters.product.ProductCreateDTOToProductEntity;
import com.example.fitness.util.converters.product.ProductEntityToProductDTO;
import com.example.fitness.util.converters.product.ProductWeightDTOToCompositionEntity;
import com.example.fitness.util.converters.recipe.RecipeCreateDTOToRecipeEntity;
import com.example.fitness.util.converters.recipe.RecipeEntityToRecipeDTO;
import com.example.fitness.util.converters.user.UserCreateDTOToUserEntity;
import com.example.fitness.util.converters.user.UserEntityToUserDTO;
import com.example.fitness.util.converters.user.UserEntityToUserToken;
import com.example.fitness.util.converters.user.UserRegistrationDTOToUserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	private final IProductService productService;

	public WebConfig(IProductService productService){
		this.productService = productService;
	}

	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {
		formatterRegistry.addConverter(new UserEntityToUserDTO());
		formatterRegistry.addConverter(new UserCreateDTOToUserEntity());
		formatterRegistry.addConverter(new LongToLocalDateTime());
		formatterRegistry.addConverter(new ProductCreateDTOToProductEntity());
		formatterRegistry.addConverter(new ProductEntityToProductDTO());
		formatterRegistry.addConverter(new RecipeCreateDTOToRecipeEntity(new ProductWeightDTOToCompositionEntity(productService)));
		formatterRegistry.addConverter(new CompositionEntityToCompositionDTO(new ProductEntityToProductDTO()));
		formatterRegistry.addConverter(new ProductWeightDTOToCompositionEntity(productService));
		formatterRegistry.addConverter(new RecipeEntityToRecipeDTO(new CompositionEntityToCompositionDTO(new ProductEntityToProductDTO())));
		formatterRegistry.addConverter(new UserRegistrationDTOToUserEntity());
		formatterRegistry.addConverter(new UserEntityToUserToken());
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}
}
