package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
@Entity
@Table(name = "M_SUBSTATION")
@Getter
public class SubstationSummary{

	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String code;
	private String name;
	@Column(name="M_ORG_ID", columnDefinition="VARCHAR2")
	private String orgId;
	private String typeOfSs;
	public SubstationSummary() {
		super();
	}
	public SubstationSummary(String id, String code, String name, String orgId, String typeOfSs) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.orgId = orgId;
		this.typeOfSs = typeOfSs;
	}
	@Override
	public String toString() {
		return "SubstationSummary [id=" + id + ", code=" + code + ", name=" + name + ", orgId=" + orgId + ", typeOfSs="
				+ typeOfSs + "]";
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getTypeOfSs() {
		return typeOfSs;
	}
	public void setTypeOfSs(String typeOfSs) {
		this.typeOfSs = typeOfSs;
	}
	
}
