package org.birlasoft.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ThirdeyeException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4919227522204402174L;

	public ThirdeyeException(String message) {
		super(message);
	}
	
	

}
