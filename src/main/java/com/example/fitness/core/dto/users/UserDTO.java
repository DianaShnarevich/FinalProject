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
	private LocalDateTime dtCreate;

	@JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
	private LocalDateTime dtUpdate;

	private String mail;
	private String fio;
	private UserRole userRole;
	private Status status;

	public UserDTO(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                   String mail, String fio, UserRole userRole, Status status) {
		this.uuid = uuid;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.mail = mail;
		this.fio = fio;
		this.userRole = userRole;
		this.status = status;
	}

	public UserDTO(UserEntity entity) {
		this.uuid = entity.getUuid();
		this.dtCreate = entity.getDtCreate();
		this.dtUpdate = entity.getDtUpdate();
		this.mail = entity.getMail();
		this.fio = entity.getFio();
		this.userRole = entity.getRole();
		this.status = entity.getStatus();
	}

	public UserDTO(){

	}

	public UserDTO(UUID uuid, String mail, String fio, UserRole role) {
	}

}
