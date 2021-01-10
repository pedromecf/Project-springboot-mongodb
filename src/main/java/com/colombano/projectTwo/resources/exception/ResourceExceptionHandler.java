package com.colombano.projectTwo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.colombano.projectTwo.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request) {		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError stError = new StandardError(System.currentTimeMillis(), status.value(), "Not found", error.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(stError);
	}
	
}
