package com.example.fitness.core.exceptions;

public class DuplicatedMailException extends RuntimeException{
	public DuplicatedMailException(String message){
		super(message);
	}
}
