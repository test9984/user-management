package org.birlasoft.usermanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class ObjectAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ObjectAlreadyExistException(String exception) {
	        super(exception);
	    }
}