package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class CompanyLocation {
	
	//districtName,stateName is for display purpose only and not stored
	private String id, companyId, type, line1, city, districtCode, districtName, stateCode, stateName, pinCode, enabled;

	public CompanyLocation() {
		// TODO Auto-generated constructor stub
	}

	public CompanyLocation(String id, String companyId, String type, String line1, String city, String districtCode,
			String districtName, String stateCode, String stateName, String pinCode, String enabled) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.type = type;
		this.line1 = line1;
		this.city = city;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.pinCode = pinCode;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "CompanyLocation [id=" + id + ", companyId=" + companyId + ", type=" + type + ", line1=" + line1
				+ ", city=" + city + ", districtCode=" + districtCode + ", districtName=" + districtName
				+ ", stateCode=" + stateCode + ", stateName=" + stateName + ", pinCode=" + pinCode + ", enabled="
				+ enabled + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	

}
