package com.example.fitness.core.dto.recipes;

import com.example.fitness.util.serializers.LocalDateTimeToMillisCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class RecipeDTO {
	private UUID uuid;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dt_create;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dt_update;

	private String title;
	private Set<CompositionDTO> composition;
	private Integer weight;
	private Integer calories;
	private BigDecimal proteins;
	private BigDecimal fats;
	private BigDecimal carbohydrates;

	public RecipeDTO(UUID uuid, LocalDateTime dt_create, LocalDateTime dt_update,
					 String title, Set<CompositionDTO> composition, Integer weight,
					 Integer calories, BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
		this.uuid = uuid;
		this.dt_create = dt_create;
		this.dt_update = dt_update;
		this.title = title;
		this.composition = composition;
		this.weight = weight;
		this.calories = calories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
	}

	public RecipeDTO() {

	}

}
