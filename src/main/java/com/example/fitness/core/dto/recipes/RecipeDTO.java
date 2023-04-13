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
	private LocalDateTime dtCreate;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dtUpdate;

	private String title;
	private Set<CompositionDTO> composition;
	private Integer weight;
	private Integer calories;
	private BigDecimal proteins;
	private BigDecimal fats;
	private BigDecimal carbohydrates;

	public RecipeDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
					 String title, Set<CompositionDTO> composition, Integer weight,
					 Integer calories, BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
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
