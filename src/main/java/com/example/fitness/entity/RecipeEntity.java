package com.example.fitness.entity;

import com.example.fitness.core.dto.recipes.RecipeCreateDTO;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "recipes")
public class RecipeEntity implements Serializable {

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

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(
			schema = "fitness",
			name = "recipe_ingredient",
			joinColumns = {@JoinColumn(name = "recipe_uuid")},
			inverseJoinColumns = {@JoinColumn(name = "ingredient_uuid")}
	)
	private Set<CompositionEntity> ingredients;

	public RecipeEntity(RecipeCreateDTO dto){
		this.dtCreate = LocalDateTime.now();
		this.title = dto.getTitle();
	}

	public RecipeEntity() {

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

	public Set<CompositionEntity> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<CompositionEntity> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RecipeEntity that = (RecipeEntity) o;
		return Objects.equals(uuid, that.uuid)
				&& Objects.equals(dtCreate, that.dtCreate)
				&& Objects.equals(dtUpdate, that.dtUpdate)
				&& Objects.equals(title, that.title)
				&& Objects.equals(ingredients, that.ingredients);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, dtCreate, dtUpdate, title, ingredients);
	}

	@Override
	public String toString() {
		return "RecipeEntity{" +
				"uuid=" + uuid +
				", dtCreate=" + dtCreate +
				", dtUpdate=" + dtUpdate +
				", title='" + title + '\'' +
				", ingredients=" + ingredients +
				'}';
	}
}
