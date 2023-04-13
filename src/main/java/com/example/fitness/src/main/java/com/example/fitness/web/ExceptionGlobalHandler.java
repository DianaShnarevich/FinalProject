package com.example.fitness.web;

import com.example.fitness.core.dto.error.ErrorResponseDTO;
import com.example.fitness.core.dto.error.StructuredErrorResponseDTO;
import com.example.fitness.core.exceptions.DuplicatedMailException;
import com.example.fitness.core.exceptions.InvalidField;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestControllerAdvice
public class ExceptionGlobalHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StructuredErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex){
		StructuredErrorResponseDTO dto = new StructuredErrorResponseDTO();
		List<ObjectError> errors =  ex.getBindingResult().getAllErrors();
		for(ObjectError error : errors){
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			InvalidField invField = new InvalidField(fieldName, errorMessage);
			dto.addError(invField);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StructuredErrorResponseDTO> handleConstraintViolation(ConstraintViolationException ex){
		StructuredErrorResponseDTO dto = new StructuredErrorResponseDTO();
		Set<ConstraintViolation<?>> violations =  ex.getConstraintViolations();
		for(ConstraintViolation<?> violation : violations){
			String fieldName = violation.getPropertyPath().toString();
			String errorMessage = violation.getMessage();
			InvalidField invField = new InvalidField(fieldName, errorMessage);
			dto.addError(invField);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponseDTO> handleTypeMismatchException(MethodArgumentTypeMismatchException e){
		ErrorResponseDTO dto = new ErrorResponseDTO("Некорректно введён " + e.getParameter().getParameterName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponseDTO> handle(HttpMessageNotReadableException e){
		ErrorResponseDTO dto = new ErrorResponseDTO("Проверьте правильность введённых данных");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handle(EntityNotFoundException e) {
		ErrorResponseDTO dto = new ErrorResponseDTO("Элемент не найден в базе данных");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(DuplicatedMailException.class)
	public ResponseEntity<ErrorResponseDTO>	handleDuplicatedMail(DuplicatedMailException e){
		ErrorResponseDTO dto = new ErrorResponseDTO(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponseDTO> handleNoElement(NoSuchElementException e){
		ErrorResponseDTO dto = new ErrorResponseDTO(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorResponseDTO> handleIllegalState(IllegalStateException e){
		ErrorResponseDTO dto = new ErrorResponseDTO(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponseDTO> handleMissingUrlParams(MissingServletRequestParameterException e){
		ErrorResponseDTO dto = new ErrorResponseDTO("В URL не переданы требуемые параметры");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponseDTO> handle(Exception e){
		ErrorResponseDTO dto = new ErrorResponseDTO("Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
	}
}
