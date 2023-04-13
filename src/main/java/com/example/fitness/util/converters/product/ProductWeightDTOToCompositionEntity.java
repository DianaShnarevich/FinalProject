package com.example.fitness.util.converters.product;

import com.example.fitness.core.dto.products.ProductWeightDTO;
import com.example.fitness.entity.CompositionEntity;
import com.example.fitness.entity.ProductEntity;
import com.example.fitness.service.api.IProductService;
import org.springframework.core.convert.converter.Converter;

public class ProductWeightDTOToCompositionEntity implements Converter<ProductWeightDTO, CompositionEntity> {

	private IProductService productService;

	public ProductWeightDTOToCompositionEntity(IProductService productService){
		this.productService = productService;
	}

	@Override
	public CompositionEntity convert(ProductWeightDTO source) {
		ProductEntity productEntity = productService.get(source.getProduct().getUuid());
		CompositionEntity compositionEntity = new CompositionEntity();
		compositionEntity.setWeight(source.getWeight());
		compositionEntity.setProduct(productEntity);
		return compositionEntity;
	}
}
