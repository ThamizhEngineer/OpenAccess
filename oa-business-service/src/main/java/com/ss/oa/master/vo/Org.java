package com.ss.oa.master.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;





@Entity

@Table(name="m_org")
@CreationTimestamp @UpdateTimestamp
@Getter 
@Scope("prototype")
public class Org implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_M_ORG_ID_SEQ")
    @SequenceGenerator(name = "id_M_ORG_ID_SEQ", sequenceName ="M_ORG_ID_SEQ")
	private String id;
	private String code;
	private String name;
	private String typeCode;
	private String parentCode;
	private String address;
	private String landline;
	private String mobile;
	private String email;
	private String ncesDivisionCode;
	private String remarks;
	
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	
	public Org() {
		super();
	}
	
	public Org(String id, String code, String name, String typeCode, String parentCode, String address, String landline,
			String mobile, String email, String ncesDivisionCode, String remarks, LocalDateTime createdDate,
			LocalDateTime modifiedDate) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.typeCode = typeCode;
		this.parentCode = parentCode;
		this.address = address;
		this.landline = landline;
		this.mobile = mobile;
		this.email = email;
		this.ncesDivisionCode = ncesDivisionCode;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "Org [id=" + id + ", code=" + code + ", name=" + name + ", typeCode=" + typeCode + ", parentCode="
				+ parentCode + ", address=" + address + ", landline=" + landline + ", mobile=" + mobile + ", email="
				+ email + ", ncesDivisionCode=" + ncesDivisionCode + ", remarks=" + remarks + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", getId()=" + getId() + ", getCode()=" + getCode()
				+ ", getName()=" + getName() + ", getTypeCode()=" + getTypeCode() + ", getParentCode()="
				+ getParentCode() + ", getAddress()=" + getAddress() + ", getLandline()=" + getLandline()
				+ ", getMobile()=" + getMobile() + ", getEmail()=" + getEmail() + ", getNcesDivisionCode()="
				+ getNcesDivisionCode() + ", getRemarks()=" + getRemarks() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedDate()=" + getModifiedDate() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNcesDivisionCode() {
		return ncesDivisionCode;
	}
	public void setNcesDivisionCode(String ncesDivisionCode) {
		this.ncesDivisionCode = ncesDivisionCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	

	
	
}
