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
public class UserLoginDTO {

	@NotBlank
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String mail;

	@NotBlank
	@Size(min = 8)
	private String password;

	public UserLoginDTO(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}

	public UserLoginDTO(){

	}
}
