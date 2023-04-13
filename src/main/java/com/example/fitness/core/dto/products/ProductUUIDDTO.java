package com.example.fitness.core.dto.products;

import com.example.fitness.util.validators.annotations.UUIDConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ProductUUIDDTO {
	@UUIDConstraint
	private UUID uuid;

	public ProductUUIDDTO(UUID uuid) {
		this.uuid = uuid;
	}

	public ProductUUIDDTO() {

	}
}
