package com.example.fitness.dao;

import com.example.fitness.entity.CompositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompositionDao extends JpaRepository<CompositionEntity, UUID> {
}
