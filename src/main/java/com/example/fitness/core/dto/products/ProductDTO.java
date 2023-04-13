package com.example.fitness.core.dto.products;

import com.example.fitness.util.serializers.LocalDateTimeToMillisCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.fitness.entity.ProductEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ProductDTO {
	private UUID uuid;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dt_create;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dt_update;

	private String title;
	private Integer weight;
	private Integer calories;
	private BigDecimal proteins;
	private BigDecimal fats;
	private BigDecimal carbohydrates;

	public ProductDTO(UUID uuid, LocalDateTime dt_create, LocalDateTime dt_update,
	                  String title, Integer weight, Integer calories,
	                  BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
		this.uuid = uuid;
		this.dt_create = dt_create;
		this.dt_update = dt_update;
		this.title = title;
		this.weight = weight;
		this.calories = calories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
	}

	public ProductDTO(ProductEntity entity){
		this.uuid = entity.getUuid();
		this.dt_create = entity.getDtCreate();
		this.dt_update = entity.getDtUpdate();
		this.title = entity.getTitle();
		this.weight = entity.getWeight();
		this.calories = entity.getCalories();
		this.proteins = entity.getProteins();
		this.fats = entity.getFats();
		this.carbohydrates = entity.getCarbohydrates();
	}

	public ProductDTO(){

	}

}
