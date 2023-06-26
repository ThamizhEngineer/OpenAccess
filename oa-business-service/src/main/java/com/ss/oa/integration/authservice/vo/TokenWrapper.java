package com.ss.oa.integration.authservice.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class TokenWrapper {
	public String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenWrapper [token=" + token + "]";
	}
	

}
