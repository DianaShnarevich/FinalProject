package com.example.fitness.service;

import com.example.fitness.entity.ProductEntity;
import com.example.fitness.dao.ProductDao;
import com.example.fitness.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ProductService implements IProductService {

	private final ProductDao repository;

	public ProductService(ProductDao repository) {
		this. repository = repository;
	}

	@Override
	public Page<ProductEntity> getPage(Integer page, Integer pageSize) {
		PageRequest pageRequest;
		if(page == null || pageSize == null) {
			pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
		}else {
			pageRequest = PageRequest.of(page, pageSize);
		}
		return repository.findAll(pageRequest);
	}

	@Override
	public ProductEntity get(UUID uuid) {
		return repository.getReferenceById(uuid);
	}

	@Override
	public void add(ProductEntity entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void delete(UUID uuid, LocalDateTime dtUpdate) {
		ProductEntity entity = repository.getReferenceById(uuid);
		if(!entity.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(dtUpdate)) {
			throw new IllegalStateException("Ошибка удаления продукта. Для удаления продукта из " +
					"справочника укажите время последнего изменения информации");
		}
		repository.delete(entity);
	}

	@Override
	public void update(ProductEntity newProduct, UUID uuid, LocalDateTime dtUpdate) {
		ProductEntity product = repository.getReferenceById(uuid);
		if(!product.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).equals(dtUpdate)) {
			throw new IllegalStateException("Ошибка изменения продукта. Для изменения информации о продукте " +
					"укажите время его последнего изменения");
		}
		product.setTitle(newProduct.getTitle());
		product.setWeight(newProduct.getWeight());
		product.setCalories(newProduct.getCalories());
		product.setProteins(newProduct.getProteins());
		product.setFats(newProduct.getFats());
		product.setCarbohydrates(newProduct.getCarbohydrates());

		repository.saveAndFlush(product);
	}
}
