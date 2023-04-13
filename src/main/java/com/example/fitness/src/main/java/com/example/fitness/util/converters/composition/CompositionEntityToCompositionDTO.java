package com.example.fitness.util.converters.composition;

import com.example.fitness.core.dto.products.ProductDTO;
import com.example.fitness.core.dto.recipes.CompositionDTO;
import com.example.fitness.entity.CompositionEntity;
import com.example.fitness.entity.ProductEntity;
import com.example.fitness.util.converters.product.ProductEntityToProductDTO;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CompositionEntityToCompositionDTO implements Converter<CompositionEntity, CompositionDTO> {

	private final ProductEntityToProductDTO converter;

	public CompositionEntityToCompositionDTO(ProductEntityToProductDTO converter){
		this.converter = converter;
	}

	@Override
	public CompositionDTO convert(CompositionEntity source) {
		ProductEntity productEntity = source.getProduct();
		ProductDTO productDTO = converter.convert(productEntity);
		CompositionDTO.CompositionDTOBuilder builder = CompositionDTO.builder();
		double productCalories = productEntity.getCalories();
		double productWeight = productEntity.getWeight();
		double entityWeight = source.getWeight();
		Integer ingredientCalories = (int)Math.round((productCalories / productWeight) * entityWeight);

		BigDecimal ingredientProteins = ((productEntity.getProteins().multiply(BigDecimal.valueOf(source.getWeight()).divide(BigDecimal.valueOf(productEntity.getWeight())).round(MathContext.DECIMAL32)))).setScale(1, RoundingMode.HALF_UP);

		BigDecimal ingredientFats = ((productEntity.getFats().multiply(BigDecimal.valueOf(source.getWeight()).divide(BigDecimal.valueOf(productEntity.getWeight()))).round(MathContext.DECIMAL32))).setScale(1, RoundingMode.HALF_UP);

		BigDecimal ingredientCarbs = ((productEntity.getCarbohydrates().multiply(BigDecimal.valueOf(source.getWeight())).divide(BigDecimal.valueOf(productEntity.getWeight()).round(MathContext.DECIMAL32)))).setScale(1, RoundingMode.HALF_UP);

		return builder.setProduct(productDTO)
				.setWeight(source.getWeight())
				.setCalories(ingredientCalories)
				.setProteins(ingredientProteins)
				.setFats(ingredientFats)
				.setCarbohydrates(ingredientCarbs).build();

	}
}
