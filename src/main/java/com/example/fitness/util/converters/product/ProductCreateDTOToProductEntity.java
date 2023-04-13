package com.example.fitness.util.converters.product;

import com.example.fitness.core.dto.products.ProductCreateDTO;
import com.example.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;

public class ProductCreateDTOToProductEntity implements Converter<ProductCreateDTO, ProductEntity> {
	@Override
	public ProductEntity convert(ProductCreateDTO source) {
		return new ProductEntity(source);
	}
}
