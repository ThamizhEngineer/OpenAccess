package com.ss.oashared.common;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OpenAccessException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpenAccessException(String exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}
}
