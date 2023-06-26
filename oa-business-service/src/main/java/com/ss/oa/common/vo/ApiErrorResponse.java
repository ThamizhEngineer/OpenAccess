package com.ss.oa.common.vo;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus; 

public class ApiErrorResponse extends ApiBaseResponse {
  

	public ApiErrorResponse() {
		super();
	    timestamp = LocalDateTime.now();
		this.status = HttpStatus.SERVICE_UNAVAILABLE;
	   }

	public  ApiErrorResponse(HttpStatus status) {
		super();
	    timestamp = LocalDateTime.now();
	    this.status = status;
	   }

	public   ApiErrorResponse(HttpStatus status, Throwable ex) {
	       this();
		   timestamp = LocalDateTime.now();
	       this.status = status;
	       this.message = "Unexpected error";
	       this.debugMessage = ex.getLocalizedMessage();
	   }

	public  ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
	       this();
		   this.timestamp = LocalDateTime.now();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	   }
	
	public  ApiErrorResponse(HttpStatus status, String message, Throwable ex, Map<String, Object> additionalData) {
	       this();
		   this.timestamp = LocalDateTime.now();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	       this.additionalData = additionalData;
	   }
	

}
