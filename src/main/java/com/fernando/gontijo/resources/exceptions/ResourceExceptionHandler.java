package com.fernando.gontijo.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fernando.gontijo.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler { // classe auxiliar que vai interceptar as excessões

	@ExceptionHandler(ObjectNotFoundException.class) // metodo que trata a exceção e retorna uma menssagem 
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	
	
	
}
