package com.example.fitness.service.api;

import com.example.fitness.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IProductService {
	Page<ProductEntity> getPage(Integer page, Integer pageSize);
	ProductEntity get(UUID uuid);
	void add(ProductEntity entity);
	void delete(UUID uuid, LocalDateTime dtUpdate);
	void update(ProductEntity newProduct, UUID uuid, LocalDateTime dtUpdate);
}
