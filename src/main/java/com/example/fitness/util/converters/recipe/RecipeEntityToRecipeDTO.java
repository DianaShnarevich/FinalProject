package com.example.fitness.util.converters.recipe;

import com.example.fitness.core.dto.recipes.CompositionDTO;
import com.example.fitness.core.dto.recipes.RecipeDTO;
import com.example.fitness.entity.CompositionEntity;
import com.example.fitness.entity.RecipeEntity;
import com.example.fitness.util.converters.composition.CompositionEntityToCompositionDTO;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class RecipeEntityToRecipeDTO implements Converter<RecipeEntity, RecipeDTO> {

	private final CompositionEntityToCompositionDTO converter;

	public RecipeEntityToRecipeDTO(CompositionEntityToCompositionDTO converter){
		this.converter = converter;
	}

	@Override
	public RecipeDTO convert(RecipeEntity source) {
		RecipeDTO recipeDTO = new RecipeDTO();
		int weight = 0;
		int calories = 0;
		BigDecimal proteins = BigDecimal.valueOf(0);
		BigDecimal fats = BigDecimal.valueOf(0);
		BigDecimal carbohydrates = BigDecimal.valueOf(0);
		Set<CompositionDTO> ingredients = new HashSet<>();
		for(CompositionEntity item : source.getIngredients()){
			CompositionDTO compositionDTO = converter.convert(item);
			ingredients.add(compositionDTO);
			weight = weight + compositionDTO.getWeight();
			calories = calories + compositionDTO.getCalories();
			proteins = proteins.add(compositionDTO.getProteins());
			fats = fats.add(compositionDTO.getFats());
			carbohydrates = carbohydrates.add(compositionDTO.getCarbohydrates());
		}

		recipeDTO.setUuid(source.getUuid());
		recipeDTO.setDtCreate(source.getDtCreate());
		recipeDTO.setDtUpdate(source.getDtUpdate());
		recipeDTO.setTitle(source.getTitle());
		recipeDTO.setComposition(ingredients);
		recipeDTO.setWeight(weight);
		recipeDTO.setCalories(calories);
		recipeDTO.setProteins(proteins);
		recipeDTO.setFats(fats);
		recipeDTO.setCarbohydrates(carbohydrates);

		return recipeDTO;
	}
}
