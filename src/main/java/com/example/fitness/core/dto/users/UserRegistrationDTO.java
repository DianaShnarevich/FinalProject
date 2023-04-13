package com.example.fitness.core.dto.users;

import jakarta.validation.constraints.NotBlank;
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
public class UserRegistrationDTO {

	@NotBlank
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String mail;

	@NotBlank
	private String fio;

	@NotBlank
	@Size(min = 8)
	private String password;

	public UserRegistrationDTO(String mail, String fio, String password) {
		this.mail = mail;
		this.fio = fio;
		this.password = password;
	}

	public UserRegistrationDTO(){

	}
}
