package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "M_ORG")
@Getter
public class OrgSummary {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String code;
	private String name;
	private String typeCode;
	private String parentCode;
	@Column(name="NCES_DIVISION_CODE", columnDefinition="VARCHAR2")
	private String ncesDivisionCode;
	private String fuelTypeCode;
	public OrgSummary() {
		super();
	}
	public OrgSummary(String id, String code, String name, String typeCode, String parentCode, String ncesDivisionCode,
			String fuelTypeCode) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.typeCode = typeCode;
		this.parentCode = parentCode;
		this.ncesDivisionCode = ncesDivisionCode;
		this.fuelTypeCode = fuelTypeCode;
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
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getNcesDivisionCode() {
		return ncesDivisionCode;
	}
	public void setNcesDivisionCode(String ncesDivisionCode) {
		this.ncesDivisionCode = ncesDivisionCode;
	}
	public String getFuelTypeCode() {
		return fuelTypeCode;
	}
	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}
	@Override
	public String toString() {
		return "OrgSummary [id=" + id + ", code=" + code + ", name=" + name + ", typeCode=" + typeCode + ", parentCode="
				+ parentCode + ", ncesDivisionCode=" + ncesDivisionCode + ", fuelTypeCode=" + fuelTypeCode + "]";
	}
	

	
}
