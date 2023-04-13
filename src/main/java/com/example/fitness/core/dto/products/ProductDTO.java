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
	private LocalDateTime dtCreate;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dtUpdate;

	private String title;
	private Integer weight;
	private Integer calories;
	private BigDecimal proteins;
	private BigDecimal fats;
	private BigDecimal carbohydrates;

	public ProductDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
	                  String title, Integer weight, Integer calories,
	                  BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.title = title;
		this.weight = weight;
		this.calories = calories;
		this.proteins = proteins;
		this.fats = fats;
		this.carbohydrates = carbohydrates;
	}

	public ProductDTO(ProductEntity entity){
		this.uuid = entity.getUuid();
		this.dtCreate = entity.getDtCreate();
		this.dtUpdate = entity.getDtUpdate();
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
