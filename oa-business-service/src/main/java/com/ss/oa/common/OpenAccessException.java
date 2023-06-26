package com.ss.oa.common;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class OpenAccessException extends RuntimeException {

	private String errCode;
	private String errDesc;
	
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public OpenAccessException(){
		super();
	}
	
	public OpenAccessException(String errCode, String errDesc){
		super();
		setErrCode(errCode);
		setErrDesc(errDesc);
	}
	
	public OpenAccessException(String message){
		super(message);
	}
	
	public OpenAccessException(Exception e){
		super(e);
	}
}
