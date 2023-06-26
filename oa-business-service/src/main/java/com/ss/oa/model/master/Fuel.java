package com.ss.oa.model.master;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "M_FUEL")
@CreationTimestamp
@UpdateTimestamp
@Entity
public class Fuel {
	@Id
	@Column(name="ID")
	private String id;
	@Column(name="FUEL_CODE")
	private String fuelCode;
	@Column(name="FUEL_NAME")
	private String fuelName;
	@Column(name="FUEL_GROUP")
	private String fuelGroup;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="CREATED_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	@Column(name="MODIFIED_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	@Column(name="IS_ENABLED")
	private char isEnabled;
	
	
	
	
	public Fuel() {
		super();
	}


	public Fuel(String id, String fuelCode, String fuelName, String fuelGroup, String createdBy, LocalDate createdDate,
			String modifiedBy, LocalDate modifiedDate, char isEnabled) {
		super();
		this.id = id;
		this.fuelCode = fuelCode;
		this.fuelName = fuelName;
		this.fuelGroup = fuelGroup;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isEnabled = isEnabled;
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


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public LocalDate getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public char getIsEnabled() {
		return isEnabled;
	}


	public void setIsEnabled(char isEnabled) {
		this.isEnabled = isEnabled;
	}


	@Override
	public String toString() {
		return "Fuel [id=" + id + ", fuelCode=" + fuelCode + ", fuelName=" + fuelName + ", fuelGroup=" + fuelGroup
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", isEnabled=" + isEnabled + "]";
	}
	
	
}
