package com.example.fitness.dao;

import com.example.fitness.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeDao extends JpaRepository<RecipeEntity, UUID> {
}
