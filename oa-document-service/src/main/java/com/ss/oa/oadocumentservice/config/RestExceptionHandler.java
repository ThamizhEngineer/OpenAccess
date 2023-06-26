package com.ss.oa.oadocumentservice.config;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ss.oa.oadocumentservice.common.vo.ApiErrorResponse;
import com.ss.oa.oadocumentservice.common.vo.ApiException;
import com.ss.oashared.common.OpenAccessException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

		Logger logger = LogManager.getLogger(this.getClass());
	  
		
	   @ExceptionHandler(ApiException.class)
	   protected ResponseEntity<Object> handleKnownApiExceptions(ApiException apiEx) {
	       ApiErrorResponse apiErrorResponse = getApiErrorResponse(apiEx);
	       apiErrorResponse.setAdditionalData(apiEx.getAdditionalData());
	       logger.debug("in custom api exception handler - {}",apiEx);
	       return buildResponseEntity(apiErrorResponse);
	   }

	   @ExceptionHandler(OpenAccessException.class)
	   protected ResponseEntity<Object> handleOAExceptions(OpenAccessException ex) {
	       ApiErrorResponse apiErrorResponse = getApiErrorResponse(ex);
	       logger.debug("in generic handler - {}", ex);
	       return buildResponseEntity(apiErrorResponse);
	   }
	
	   @ExceptionHandler(Exception.class)
	   protected ResponseEntity<Object> handleGenericExceptions(Exception ex) {
	       ApiErrorResponse apiErrorResponse = getApiErrorResponse(ex);
	       logger.debug("in generic handler - {}", ex);
	       return buildResponseEntity(apiErrorResponse);
	   }
	
	   private ApiErrorResponse getApiErrorResponse(Exception e) {
		   int maxStraceLength = 2000;
		   int maxMessageLength = 100;
		   int straceLength = 0;
		   int messageLength = 0;
		   String debugMessage="";
		   
		   ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		   messageLength = findMaxLength(e.getMessage(), maxMessageLength);
		   if(messageLength>0)
			   apiErrorResponse.setMessage(e.getMessage().substring(0, messageLength-1));
		   StringWriter sw = new StringWriter();
           e.printStackTrace(new PrintWriter(sw)); 
           debugMessage = sw.toString();
           straceLength = findMaxLength(debugMessage, maxStraceLength);
           if(straceLength>0)
        	   apiErrorResponse.setDebugMessage(debugMessage.substring(0, straceLength-1));
           logger.debug("straceLength-"+straceLength+" ,messageLength-"+messageLength);
	       return apiErrorResponse;
	   }
	   
	   private int findMaxLength(String payload, int configuredMaxLength) {
		   int maxLength = 0;	
		   if(!(payload==null || payload.trim().isEmpty())) {
			   if(payload.trim().length() > configuredMaxLength ) {
				   maxLength = configuredMaxLength;
			   }
			   else {
				   maxLength = payload.trim().length();
			   }
		   }
		   return maxLength;
	   }
	   private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse) {
	       return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
	   }


}
