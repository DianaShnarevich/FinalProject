package com.example.fitness.entity;

import com.example.fitness.core.dto.products.ProductCreateDTO;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "products")
public class ProductEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uuid")
	private UUID uuid;

	@CreatedDate
	@Column(name = "dt_create")
	private LocalDateTime dtCreate;

	@Version
	@Column(name = "dt_update")
	private LocalDateTime dtUpdate;

	@Column(name = "title")
	private String title;

	@Column(name = "weight")
	private Integer weight;

	@Column(name = "calories")
	private Integer calories;

	@Column(name = "proteins", precision = 10, scale = 1)
	private BigDecimal proteins;

	@Column(name = "fats", precision = 10, scale = 1)
	private BigDecimal fats;

	@Column(name = "carbohydrates", precision = 10, scale = 1)
	private BigDecimal carbohydrates;

	public ProductEntity(ProductCreateDTO dto) {
		this.dtCreate = LocalDateTime.now();
		this.title = dto.getTitle();
		this.weight = dto.getWeight();
		this.calories = dto.getCalories();
		this.proteins = dto.getProteins();
		this.fats = dto.getFats();
		this.carbohydrates = dto.getCarbohydrates();
	}

	public ProductEntity() {

	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public BigDecimal getProteins() {
		return proteins;
	}

	public void setProteins(BigDecimal proteins) {
		this.proteins = proteins;
	}

	public BigDecimal getFats() {
		return fats;
	}

	public void setFats(BigDecimal fats) {
		this.fats = fats;
	}

	public BigDecimal getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(BigDecimal carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductEntity that = (ProductEntity) o;
		return Objects.equals(uuid, that.uuid)
				&& Objects.equals(dtCreate, that.dtCreate)
				&& Objects.equals(dtUpdate, that.dtUpdate)
				&& Objects.equals(title, that.title)
				&& Objects.equals(weight, that.weight)
				&& Objects.equals(calories, that.calories)
				&& Objects.equals(proteins, that.proteins)
				&& Objects.equals(fats, that.fats)
				&& Objects.equals(carbohydrates, that.carbohydrates);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, dtCreate, dtUpdate, title, weight, calories, proteins, fats, carbohydrates);
	}

	@Override
	public String toString() {
		return "ProductEntity{" +
				"uuid=" + uuid +
				", dtCreate=" + dtCreate +
				", dtUpdate=" + dtUpdate +
				", title='" + title + '\'' +
				", weight=" + weight +
				", calories=" + calories +
				", proteins=" + proteins +
				", fates=" + fats +
				", carbohydrates=" + carbohydrates +
				'}';
	}
}
