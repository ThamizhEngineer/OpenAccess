package com.ss.oa.integration.authservice.vo;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Scope("prototype")
public class ProfileAccess {

	public ProfileAccess() {
		super();
	}
	@JsonProperty("systemKey") private String systemKey;
	@JsonProperty("functionality") private String functionality;
	@JsonProperty("feature") private String feature;
	@JsonProperty("orgType") private String orgType;
	@Override
	public String toString() {
		return "ProfileAccess [systemKey=" + systemKey + ", functionality=" + functionality + ", feature=" + feature
				+ ", orgType=" + orgType + "]";
	}
	public ProfileAccess(String systemKey, String functionality, String feature, String orgType) {
		super();
		this.systemKey = systemKey;
		this.functionality = functionality;
		this.feature = feature;
		this.orgType = orgType;
	}
	public String getSystemKey() {
		return systemKey;
	}
	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	
	
	

}
