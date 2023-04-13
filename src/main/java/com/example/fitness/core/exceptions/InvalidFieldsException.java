package com.example.fitness.core.exceptions;

import java.util.ArrayList;
import java.util.List;

public class InvalidFieldsException extends RuntimeException{
	private final List<InvalidField> invalidFieldsList = new ArrayList<>();

	public void add(InvalidField obj){
		invalidFieldsList.add(obj);
	}

	public List<InvalidField> getInvalidFieldsList() {
		return invalidFieldsList;
	}
}
