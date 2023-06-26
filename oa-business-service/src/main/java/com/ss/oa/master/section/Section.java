package com.ss.oa.master.section;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="M_SECTION")
@Entity
public class Section implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_M_SECTION_ID_SEQ")
    @SequenceGenerator(name = "id_M_SECTION_ID_SEQ", sequenceName ="M_SECTION_ID_SEQ")
	private String id;
	private String sectionCode;
	private String sectionName;
	private String mOrgId;
	@Formula("(SELECT org.NAME FROM M_ORG org WHERE org.ID=m_org_id)")
	private String mOrgName;
	private String remarks;
	private String parentCode;
	private String enabled;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	public Section() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Section(String id, String sectionCode, String sectionName, String mOrgId, String mOrgName, String remarks,
			String parentCode, String enabled, String createdBy, String modifiedBy, LocalDate createdDate,
			LocalDate modifiedDate) {
		super();
		this.id = id;
		this.sectionCode = sectionCode;
		this.sectionName = sectionName;
		this.mOrgId = mOrgId;
		this.mOrgName = mOrgName;
		this.remarks = remarks;
		this.parentCode = parentCode;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getmOrgId() {
		return mOrgId;
	}
	public void setmOrgId(String mOrgId) {
		this.mOrgId = mOrgId;
	}
	public String getmOrgName() {
		return mOrgName;
	}
	public void setmOrgName(String mOrgName) {
		this.mOrgName = mOrgName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Section [id=" + id + ", sectionCode=" + sectionCode + ", sectionName=" + sectionName + ", mOrgId="
				+ mOrgId + ", mOrgName=" + mOrgName + ", remarks=" + remarks + ", parentCode=" + parentCode
				+ ", enabled=" + enabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
}
