package com.example.fitness.core.dto.users;

import com.example.fitness.core.enums.UserRole;
import com.example.fitness.core.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class UserCreateDTO {
	@NotBlank
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String mail;

	@NotBlank
	private String fio;

	@NotNull
	private UserRole userRole;

	@NotNull
	private Status status;

	@NotBlank
	@Size(min = 8)
	private String password;

	public UserCreateDTO(String mail, String fio, UserRole userRole, Status status, String password) {
		this.mail = mail;
		this.fio = fio;
		this.userRole = userRole;
		this.status = status;
		this.password = password;
	}

	public UserCreateDTO(){

	}

}
