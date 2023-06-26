package com.ss.oa.report.vo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name ="M_FUEL")
@Getter
public class FuelSummary {

	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	@Column(name="FUEL_CODE", columnDefinition="VARCHAR2")
	private String fuelCode;
	@Column(name="FUEL_NAME", columnDefinition="VARCHAR2")
	private String fuelName;
	@Column(name="FUEL_GROUP", columnDefinition="VARCHAR2")
	private String fuelGroup;
	
	
	public FuelSummary () {
		super();
	}


	public FuelSummary(String id, String fuelCode, String fuelName, String fuelGroup) {
		super();
		this.id = id;
		this.fuelCode = fuelCode;
		this.fuelName = fuelName;
		this.fuelGroup = fuelGroup;
	}


	@Override
	public String toString() {
		return "FuelSummary [id=" + id + ", fuelCode=" + fuelCode + ", fuelName=" + fuelName + ", fuelGroup="
				+ fuelGroup + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFuelCode() {
		return fuelCode;
	}


	public void setFuelCode(String fuelCode) {
		this.fuelCode = fuelCode;
	}


	public String getFuelName() {
		return fuelName;
	}


	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}


	public String getFuelGroup() {
		return fuelGroup;
	}


	public void setFuelGroup(String fuelGroup) {
		this.fuelGroup = fuelGroup;
	}

	
	
	
	
}
