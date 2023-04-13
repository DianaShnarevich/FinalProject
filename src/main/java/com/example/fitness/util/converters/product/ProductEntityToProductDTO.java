package com.example.fitness.util.converters.product;

import com.example.fitness.core.dto.products.ProductDTO;
import com.example.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;

public class ProductEntityToProductDTO implements Converter<ProductEntity, ProductDTO> {
	@Override
	public ProductDTO convert(ProductEntity source) {
		return new ProductDTO(source);
	}
}
