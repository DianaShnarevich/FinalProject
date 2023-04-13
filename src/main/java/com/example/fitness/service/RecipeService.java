package com.example.fitness.service;

import com.example.fitness.entity.RecipeEntity;
import com.example.fitness.dao.RecipeDao;
import com.example.fitness.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class RecipeService implements IRecipeService {

	private final RecipeDao repository;

	public RecipeService(RecipeDao repository){
		this.repository = repository;
	}

	@Override
	public Page<RecipeEntity> getPage(Integer page, Integer pageSize) {
		PageRequest pageRequest;
		if(page == null || pageSize == null) {
			pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
		}else {
			pageRequest = PageRequest.of(page, pageSize);
		}
		return repository.findAll(pageRequest);
	}

	@Override
	public RecipeEntity get(UUID uuid) {
		return repository.getReferenceById(uuid);
	}

	@Override
	public void add(RecipeEntity entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		RecipeEntity entity = repository.getReferenceById(uuid);  //EntityNotFoundException
		if(!entity.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(dtUpdate)) {
			throw new IllegalStateException("Рецепт был изменён ранее. Для удаления получите актуальную версию");
		}
		repository.delete(entity);
	}

	@Override
	public void update(RecipeEntity newRecipe, UUID uuid, LocalDateTime dtUpdate) {
		RecipeEntity recipe = repository.getReferenceById(uuid); //EntityNotFoundException
		if(!recipe.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(dtUpdate)) {
			throw new IllegalStateException("Рецепт был изменён ранее");
		}

		recipe.setTitle(newRecipe.getTitle());
		recipe.setIngredients(newRecipe.getIngredients());

		repository.saveAndFlush(recipe);
	}
}
