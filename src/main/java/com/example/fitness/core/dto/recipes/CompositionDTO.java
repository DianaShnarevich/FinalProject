package com.example.fitness.core.dto.recipes;

import com.example.fitness.core.dto.products.ProductDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class CompositionDTO {
	private ProductDTO product;
	private Integer weight;
	private Integer calories;
	private BigDecimal proteins;
	private BigDecimal fats;
	private BigDecimal carbohydrates;

	private CompositionDTO(CompositionDTO dto) {
		this.product = dto.product;
		this.weight = dto.weight;
		this.calories = dto.calories;
		this.proteins = dto.proteins;
		this.fats = dto.fats;
		this.carbohydrates = dto.carbohydrates;
	}

	private CompositionDTO() {

	}

	public static CompositionDTOBuilder builder(){
		return new CompositionDTOBuilder();
	}

	public static class CompositionDTOBuilder {

		private final CompositionDTO ingredient = new CompositionDTO();

		public CompositionDTOBuilder setProduct(ProductDTO product) {
			ingredient.product = product;
			return this;
		}

		public CompositionDTOBuilder setWeight(Integer weight) {
			ingredient.weight = weight;
			return this;
		}

		public CompositionDTOBuilder setCalories(Integer calories) {
			ingredient.calories = calories;
			return this;
		}

		public CompositionDTOBuilder setProteins(BigDecimal proteins) {
			ingredient.proteins = proteins;
			return this;
		}

		public CompositionDTOBuilder setFats(BigDecimal fats) {
			ingredient.fats = fats;
			return this;
		}

		public CompositionDTOBuilder setCarbohydrates(BigDecimal carbohydrates) {
			ingredient.carbohydrates = carbohydrates;
			return this;
		}

		public CompositionDTO build(){
			return new CompositionDTO(ingredient);
		}
	}
}
