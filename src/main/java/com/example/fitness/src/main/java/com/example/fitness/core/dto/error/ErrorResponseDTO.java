package com.example.fitness.core.dto.error;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
@EqualsAndHashCode
@Getter
@ToString
public class ErrorResponseDTO {
	private final String logref = "error";
	private String message;

	public ErrorResponseDTO(String message) {
		this.message = message;
	}

	public ErrorResponseDTO(){

	}

	public String getLogref() {
		return logref;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
