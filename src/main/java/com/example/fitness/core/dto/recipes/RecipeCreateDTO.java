package com.example.fitness.core.dto.recipes;

import com.example.fitness.core.dto.products.ProductWeightDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class RecipeCreateDTO {
	@NotBlank
	private String title;
	private Set<@Valid ProductWeightDTO> composition;

	public RecipeCreateDTO(String title, Set<ProductWeightDTO> composition) {
		this.title = title;
		this.composition = composition;
	}

	public RecipeCreateDTO(){

	}
}
