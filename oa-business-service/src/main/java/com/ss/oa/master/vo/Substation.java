package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Substation {
	
	private String id, code, name, remarks, orgId, orgName; 
	public Substation() {
		super();
	}
	
	public Substation(String id, String code, String name, String remarks, String orgId, String orgName) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.remarks = remarks;
		this.orgId = orgId;
		this.orgName = orgName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return "Substation [id=" + id + ", code=" + code + ", name=" + name + ", remarks=" + remarks + ", orgId="
				+ orgId + ", orgName="+ orgName + "]";
	}
	

}
