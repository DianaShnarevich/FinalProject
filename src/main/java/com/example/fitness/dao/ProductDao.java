package com.example.fitness.dao;

import com.example.fitness.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductDao extends JpaRepository<ProductEntity, UUID>{
}
