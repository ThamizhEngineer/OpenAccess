package com.ss.oa.common.vo;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class ApiSuccessResponse extends ApiBaseResponse {

	public ApiSuccessResponse() {
	       super();
	       timestamp = LocalDateTime.now();
	       this.status = HttpStatus.OK;
	   }

	public  ApiSuccessResponse(HttpStatus status) {
		   super();
	       timestamp = LocalDateTime.now();
	       this.status = status;
	   }

	public   ApiSuccessResponse(HttpStatus status, String message, Map<String, Object> additionalData) {
	       this();
	       timestamp = LocalDateTime.now();
	       this.status = status;
	       this.message = message;
	       this.additionalData = additionalData;
	   }

	

}
