package com.example.fitness.util.converters.recipe;

import com.example.fitness.core.dto.products.ProductWeightDTO;
import com.example.fitness.core.dto.recipes.RecipeCreateDTO;
import com.example.fitness.entity.CompositionEntity;
import com.example.fitness.entity.RecipeEntity;
import com.example.fitness.util.converters.product.ProductWeightDTOToCompositionEntity;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class RecipeCreateDTOToRecipeEntity implements Converter<RecipeCreateDTO, RecipeEntity> {

	private final ProductWeightDTOToCompositionEntity converter;

	public RecipeCreateDTOToRecipeEntity(ProductWeightDTOToCompositionEntity converter){
		this.converter = converter;
	}

	@Override
	public RecipeEntity convert(RecipeCreateDTO source) {
		Set<CompositionEntity> ingredients = new HashSet<>();

		for(ProductWeightDTO item : source.getComposition()){
			CompositionEntity ingredient = converter.convert(item);
			ingredients.add(ingredient);
		}

		RecipeEntity recipe = new RecipeEntity();
		recipe.setDtCreate(LocalDateTime.now());
		recipe.setTitle(source.getTitle());
		recipe.setIngredients(ingredients);

		return recipe;
	}
}
