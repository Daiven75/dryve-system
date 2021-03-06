package com.lucasilva.dryve.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucasilva.dryve.service.exceptions.BoardAlreadyExistsException;
import com.lucasilva.dryve.service.exceptions.ObjectNotFoundException;
import com.lucasilva.dryve.service.exceptions.YearNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(
			ObjectNotFoundException e,
			HttpServletRequest request) {
		
		StandardError err = new StandardError(
				HttpStatus.NOT_FOUND.value(), 
				e.getMessage().substring(0, 8),
				e.getMessage().substring(11, e.getMessage().length()), 
				System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(BoardAlreadyExistsException.class)
	public ResponseEntity<StandardError> boardNotFound(
			BoardAlreadyExistsException e,
			HttpServletRequest request) {
		
		StandardError err = new StandardError(
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage().substring(0, 8),
				e.getMessage().substring(11, e.getMessage().length()),
				System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(YearNotFoundException.class)
	public ResponseEntity<StandardError> yearNotFound(
			YearNotFoundException e,
			HttpServletRequest request) {
		
		StandardError err = new StandardError(
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage().substring(0, 8),
				e.getMessage().substring(11, e.getMessage().length()),
				System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(
			MethodArgumentNotValidException e,
			HttpServletRequest request) {
		
		ValidationError err = new ValidationError(
				HttpStatus.BAD_REQUEST.value(),
				"DRV-0000",
				"Erro de valida????o", 
				System.currentTimeMillis());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}