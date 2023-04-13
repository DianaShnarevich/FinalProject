package com.example.fitness.web.controllers;

import com.example.fitness.core.dto.PageDTO;
import com.example.fitness.core.dto.recipes.RecipeCreateDTO;
import com.example.fitness.core.dto.recipes.RecipeDTO;
import com.example.fitness.entity.RecipeEntity;
import com.example.fitness.service.api.IRecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/recipe")
@Validated
public class RecipeController {

	private final IRecipeService recipeService;
	private final ConversionService conversionService;

	public RecipeController(IRecipeService recipeService,
	                        ConversionService conversionService){
		this.recipeService = recipeService;
		this.conversionService = conversionService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addRecipe(@Valid @RequestBody RecipeCreateDTO dto){
		RecipeEntity recipeEntity = conversionService.convert(dto, RecipeEntity.class);
		recipeService.add(recipeEntity);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body("Рецепт добавлен");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<PageDTO<RecipeDTO>> getPage(
			@RequestParam(name = "page", required = false, defaultValue = "0")
			Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "20")
			Integer size){

		Page<RecipeEntity> recipes = recipeService.getPage(page, size);
		Set<RecipeDTO> recipesSet = new HashSet<>();
		for(RecipeEntity item : recipes.getContent()){
			RecipeDTO dto = conversionService.convert(item, RecipeDTO.class);
			recipesSet.add(dto);
		}

		PageDTO.PageBuilder<RecipeDTO> builder = PageDTO.builder();
		PageDTO<RecipeDTO> pageOfRecipes = builder.setNumber(recipes.getNumber())
				.setSize(recipes.getSize())
				.setTotal_pages(recipes.getTotalPages())
				.setTotal_elements(recipes.getTotalElements())
				.setFirst(recipes.isFirst())
				.setLast(recipes.isLast())
				.setNumber_of_elements(recipes.getNumberOfElements())
				.setContent(recipesSet)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(pageOfRecipes);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{uuid}/dt_update/{dt_update}")
	public ResponseEntity<?> updateRecipe(@PathVariable(name = "uuid") UUID uuid,
	                                       @PathVariable(name = "dt_update") Long dtUpdate,
	                                      @Valid @RequestBody RecipeCreateDTO dto) {
		RecipeEntity entity = conversionService.convert(dto, RecipeEntity.class);
		LocalDateTime dt = conversionService.convert(dtUpdate, LocalDateTime.class);
		recipeService.update(entity, uuid, dt);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("Рецепт обновлен");
	}
}
