package com.example.fitness.core.dto.products;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ProductCreateDTO {
	@NotBlank
	private String title;

	@NotNull
	@PositiveOrZero
	private Integer weight;

	@NotNull
	@PositiveOrZero
	private Integer calories;

	@NotNull
	@PositiveOrZero
	@Digits(integer = 5, fraction = 1)
	private BigDecimal proteins;

	@NotNull
	@PositiveOrZero
	@Digits(integer = 5, fraction = 1)
	private BigDecimal fats;

	@NotNull
	@PositiveOrZero
	@Digits(integer = 5, fraction = 1)
	private BigDecimal carbohydrates;

	public ProductCreateDTO(String title, Integer weight, Integer calories,
	                        BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
		this.title = title;
		this.weight = weight;
		this.calories = calories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
	}

	public ProductCreateDTO(){

	}
}
