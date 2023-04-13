package com.example.fitness.core.dto.products;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ProductWeightDTO {
	@Valid
	private ProductUUIDDTO product;

	@NotNull
	@Positive
	private Integer weight;

	public ProductWeightDTO(ProductUUIDDTO product, Integer weight) {
		this.product = product;
		this.weight = weight;
	}

	public ProductWeightDTO(){

	}

}
