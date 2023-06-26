package com.ss.oa.common.vo;

import java.util.HashMap;
import java.util.Map;

public class ApiException extends RuntimeException 
{

	Map<String, Object> additionalData = new HashMap<String, Object>();
	private static final long serialVersionUID = 1006346570770765823L;

	public ApiException(Exception e, Map<String, Object> additionalData) {
        super(e);
        this.additionalData = additionalData;
    }
	public ApiException(String exception, Map<String, Object> additionalData) {
        super(exception);
        this.additionalData = additionalData;
    }

	public Map<String, Object> getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(Map<String, Object> additionalData) {
		this.additionalData = additionalData;
	}
	
}
