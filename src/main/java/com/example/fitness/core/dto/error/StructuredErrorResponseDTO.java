package com.example.fitness.core.dto.error;

import com.example.fitness.core.exceptions.InvalidField;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
public class StructuredErrorResponseDTO {
	private final String logref = "structured_error";
	private List<InvalidField> errors = new ArrayList<>();

	public StructuredErrorResponseDTO(){

	}

	public String getLogref() {
		return logref;
	}

	public List<InvalidField> getErrors() {
		return errors;
	}

	public void addError(InvalidField invalidField) {
		this.errors.add(invalidField);
	}
}
