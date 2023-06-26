package com.ss.oashared.model;

import java.util.Map;

public class Response {
	public String id,code,result; //SUCCESS or "FAILURE" 
	public String message;
	public Map<String,String> details; 
		//details can have "ID", "CODE", "ERROR_DETAILS" and more data
	
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
		public Response(String id, String code, String result, String message, Map<String, String> details) {
			super();
			this.id = id;
			this.code = code;
			this.result = result;
			this.message = message;
			this.details = details;
		}
		@Override
		public String toString() {
			return "Response [id=" + id + ", code=" + code + ", result=" + result + ", message=" + message
					+ ", details=" + details + "]";
		}
		public String getId() {
			return id;
		}
		public String getCode() {
			return code;
		}
		public String getResult() {
			return result;
		}
		public String getMessage() {
			return message;
		}
		public Map<String, String> getDetails() {
			return details;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public void setDetails(Map<String, String> details) {
			this.details = details;
		}

		public static SimpleResponse returnSuccess() {
			 return new SimpleResponse("SUCCESS");
		}
		public static SimpleResponse returnFailure() {
			// TODO Auto-generated method stub
			return new SimpleResponse("FAILURE");
		}
	
}
