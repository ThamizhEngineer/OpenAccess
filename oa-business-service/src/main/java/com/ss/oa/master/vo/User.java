package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class User {
	private String id, firstName, lastName, type, isSuperUser, enabled, orgId, companyId;
	


	public User(String id, String firstName, String lastName, String type, String isSuperUser, String enabled,
			String orgId, String companyId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.isSuperUser = isSuperUser;
		this.enabled = enabled;
		this.orgId = orgId;
		this.companyId = companyId;
	}


	public String getIsSuperUser() {
		return isSuperUser;
	}


	public void setIsSuperUser(String isSuperUser) {
		this.isSuperUser = isSuperUser;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type
				+ ", isSuperUser=" + isSuperUser + ", enabled=" + enabled + ", orgId=" + orgId + ", companyId="
				+ companyId + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public User() {
		super();
	}

}
