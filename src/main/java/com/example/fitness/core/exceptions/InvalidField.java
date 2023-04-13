package com.example.fitness.core.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@EqualsAndHashCode
@ToString
@Getter
public class InvalidField {
	private final String field;
	private final String message;

	public InvalidField(String field, String message) {
		this.field = field;
		this.message = message;
	}

}
