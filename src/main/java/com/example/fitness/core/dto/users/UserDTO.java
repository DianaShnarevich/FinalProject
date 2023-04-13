package com.example.fitness.core.dto.users;

import com.example.fitness.core.enums.Status;
import com.example.fitness.core.enums.UserRole;
import com.example.fitness.util.serializers.LocalDateTimeToMillisCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.fitness.entity.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class UserDTO {
	private UUID uuid;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dt_create;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dt_update;

	private String mail;
	private String fio;
	private UserRole role;
	private Status status;

	public UserDTO(UUID uuid, LocalDateTime dt_create, LocalDateTime dt_update,
                   String mail, String fio, UserRole role, Status status) {
		this.uuid = uuid;
		this.dt_create = dt_create;
		this.dt_update = dt_update;
		this.mail = mail;
		this.fio = fio;
		this.role = role;
		this.status = status;
	}

	public UserDTO(UserEntity entity) {
		this.uuid = entity.getUuid();
		this.dt_create = entity.getDtCreate();
		this.dt_update = entity.getDtUpdate();
		this.mail = entity.getMail();
		this.fio = entity.getFio();
		this.role = entity.getRole();
		this.status = entity.getStatus();
	}

	public UserDTO(){

	}

	public UserDTO(UUID uuid, String mail, String fio, UserRole role) {
	}

}
