package com.example.fitness.entity;

import com.example.fitness.core.dto.users.UserCreateDTO;
import com.example.fitness.core.dto.users.UserRegistrationDTO;
import com.example.fitness.core.enums.Status;
import com.example.fitness.core.enums.UserRole;
import com.example.fitness.util.generators.VerificationCodeGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name ="users")
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uuid")
	private UUID uuid;

	@CreatedDate
	@Column(name = "dt_create")
	private LocalDateTime dt_create;

	@Version
	@Column(name = "dt_update")
	private LocalDateTime dt_update;

	@Column(name = "mail", unique = true)
	private String mail;

	@Column(name = "fio")
	private String fio;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "password")
	private String password;

	@JsonIgnore
	private String code;

	public UserEntity(){

	}

	public UserEntity(UserCreateDTO dto){
		this.dt_create = LocalDateTime.now();
		this.mail = dto.getMail();
		this.fio = dto.getFio();
		this.role = dto.getUserRole();
		this.status = dto.getStatus();
		this.password = dto.getPassword();
		this.code = VerificationCodeGenerator.generate();
	}

	public UserEntity(UserRegistrationDTO dto){
		this.dt_create = LocalDateTime.now();
		this.mail = dto.getMail();
		this.fio = dto.getFio();
		this.role = UserRole.USER;
		this.status = Status.WAITING_ACTIVATION;
		this.password = dto.getPassword();
		this.code = VerificationCodeGenerator.generate();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getDtCreate() {
		return dt_create;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dt_create = dtCreate;
	}

	public LocalDateTime getDtUpdate() {
		return dt_update;
	}

	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dt_update = dtUpdate;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole userRole) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity that = (UserEntity) o;
		return Objects.equals(uuid, that.uuid)
				&& Objects.equals(dt_create, that.dt_create)
				&& Objects.equals(dt_update, that.dt_update)
				&& Objects.equals(mail, that.mail)
				&& Objects.equals(fio, that.fio)
				&& role == that.role
				&& status == that.status
				&& Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, dt_create, dt_update, mail, fio, role, status, password);
	}

	@Override
	public String toString() {
		return "UserEntity{" +
				"uuid=" + uuid +
				", dtCreate=" + dt_create +
				", dtUpdate=" + dt_update +
				", mail='" + mail + '\'' +
				", fio='" + fio + '\'' +
				", role=" + role +
				", status=" + status +
				", password='" + password + '\'' +
				'}';
	}
}
