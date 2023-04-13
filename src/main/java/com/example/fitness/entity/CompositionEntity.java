package com.example.fitness.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "ingredients")
public class CompositionEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uuid")
	private UUID uuid;

	@Column(name = "weight", nullable = false)
	private Integer weight;

	@ManyToOne
	@JoinColumn(name = "product_uuid", referencedColumnName = "uuid", nullable = false)
	private ProductEntity product;

	public CompositionEntity() {

	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CompositionEntity that = (CompositionEntity) o;
		return Objects.equals(uuid, that.uuid)
				&& Objects.equals(weight, that.weight)
				&& Objects.equals(product, that.product);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, weight, product);
	}

	@Override
	public String toString() {
		return "IngredientEntity{" +
				"uuid=" + uuid +
				", weight=" + weight +
				", product=" + product +
				'}';
	}
}
