package com.ss.oa.model.transaction;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "A_UNALLOCATED_SERVICE")
public class UnAllocatedRemarks {
	
	
	@Id
	@Column(name="ID")
	private String id;
	@Column(name="M_COMP_SERVICE_NUMBER")
	private String mCompServiceNumber;
	@Column(name="DISP_COMPANY_NAME")
	private String dispCompanyName;
	@Column(name="DISP_ORG_NAME")
	private String dispOrgName;
	@Column(name="STMT_GEN_DATE")
	private Date stmtGenDate;
	@Column(name="NET_GENERATION")
	private String netGeneration;
	@Column(name="TOTAL_CHARGES")
	private String totslCharges;
	@Column(name="STMT_MONTH")
	private String stmtMonth;
	@Column(name="STMT_YEAR")
	private String stmtYear;
	@Column(name="M_ORG_ID")
	private String mOrgId;
	@Column(name="IS_CAPTIVE")
	private String isCaptive;
	@Column(name="IS_STB")
	private String isStb;
	@Column(name="AUDIT_REMARKS")
	private String auditRemarks;
	@Column(name="DELETE_REMARKS")
	private String deleteRemarks;
	@Column(name="IS_DELETED")
	private String isDeleted;
	
	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="DELETED_DT")
	private LocalDate deletedDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="CREATED_DT")
	private LocalDate createdDt;
    
    
    
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="MODIFIED_BY")
	private String modifiedBy;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="MODIFIED_DATE")
	private LocalDate modifiedDate;
	
	
	@Column(name="T_GEN_STMT_ID")
	private String tGenStmtId;
	@Column(name="M_COMPANY_ID")
	private String mCompanyId;
	@Column(name="M_COMP_SERVICE_ID")
	private String mCompServiceId;
	@Column(name="EXCESS_UNIT_TYPE")
	private String excessUnitType;
	@Column(name="IS_THIRD_PARTY")
	private String isThirdParty;
	@Column(name ="FLOW_TYPE_CODE")
	private String flowTypeCode;
	
	
	
	
	
	public UnAllocatedRemarks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UnAllocatedRemarks(String id, String mCompServiceNumber, String dispCompanyName, String dispOrgNameString,
			Date stmtGenDate, String netGeneration, String totslCharges, String stmtMonth, String stmtYear,
			String mOrgId, String isCaptive, String isStb, String auditRemarks, String deleteRemarks, String isDeleted,
			LocalDate deletedDt, LocalDate createdDt, String createdBy, String modifiedBy, LocalDate modifiedDate,
			String tGenStmtId, String mCompanyId, String mCompServiceId, String excessUnitType, String isThirdParty,
			String flowTypeCode) {
		super();
		this.id = id;
		this.mCompServiceNumber = mCompServiceNumber;
		this.dispCompanyName = dispCompanyName;
		this.dispOrgName = dispOrgNameString;
		this.stmtGenDate = stmtGenDate;
		this.netGeneration = netGeneration;
		this.totslCharges = totslCharges;
		this.stmtMonth = stmtMonth;
		this.stmtYear = stmtYear;
		this.mOrgId = mOrgId;
		this.isCaptive = isCaptive;
		this.isStb = isStb;
		this.auditRemarks = auditRemarks;
		this.deleteRemarks = deleteRemarks;
		this.isDeleted = isDeleted;
		this.deletedDt = deletedDt;
		this.createdDt = createdDt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.tGenStmtId = tGenStmtId;
		this.mCompanyId = mCompanyId;
		this.mCompServiceId = mCompServiceId;
		this.excessUnitType = excessUnitType;
		this.isThirdParty = isThirdParty;
		this.flowTypeCode = flowTypeCode;
	}
	@Override
	public String toString() {
		return "UnAllocatedRemarks [id=" + id + ", mCompServiceNumber=" + mCompServiceNumber + ", dispCompanyName="
				+ dispCompanyName + ", dispOrgNameString=" + dispOrgName + ", stmtGenDate=" + stmtGenDate
				+ ", netGeneration=" + netGeneration + ", totslCharges=" + totslCharges + ", stmtMonth=" + stmtMonth
				+ ", stmtYear=" + stmtYear + ", mOrgId=" + mOrgId + ", isCaptive=" + isCaptive + ", isStb=" + isStb
				+ ", auditRemarks=" + auditRemarks + ", deleteRemarks=" + deleteRemarks + ", isDeleted=" + isDeleted
				+ ", deletedDt=" + deletedDt + ", createdDt=" + createdDt + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", tGenStmtId=" + tGenStmtId + ", mCompanyId="
				+ mCompanyId + ", mCompServiceId=" + mCompServiceId + ", excessUnitType=" + excessUnitType
				+ ", isThirdParty=" + isThirdParty + ", flowTypeCode=" + flowTypeCode + ", getId()=" + getId()
				+ ", getmCompServiceNumber()=" + getmCompServiceNumber() + ", getDispCompanyName()="
				+ getDispCompanyName() + ", getDispOrgNameString()=" + getDispOrgName() + ", getStmtGenDate()="
				+ getStmtGenDate() + ", getNetGeneration()=" + getNetGeneration() + ", getTotslCharges()="
				+ getTotslCharges() + ", getStmtMonth()=" + getStmtMonth() + ", getStmtYear()=" + getStmtYear()
				+ ", getmOrgId()=" + getmOrgId() + ", getIsCaptive()=" + getIsCaptive() + ", getIsStb()=" + getIsStb()
				+ ", getAuditRemarks()=" + getAuditRemarks() + ", getDeleteRemarks()=" + getDeleteRemarks()
				+ ", getIsDeleted()=" + getIsDeleted() + ", getDeletedDt()=" + getDeletedDt() + ", getCreatedDt()="
				+ getCreatedDt() + ", getCreatedBy()=" + getCreatedBy() + ", getModifiedBy()=" + getModifiedBy()
				+ ", getModifiedDate()=" + getModifiedDate() + ", gettGenStmtId()=" + gettGenStmtId()
				+ ", getmCompanyId()=" + getmCompanyId() + ", getmCompServiceId()=" + getmCompServiceId()
				+ ", getExcessUnitType()=" + getExcessUnitType() + ", getIsThirdParty()=" + getIsThirdParty()
				+ ", getFlowTypeCode()=" + getFlowTypeCode() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getmCompServiceNumber() {
		return mCompServiceNumber;
	}
	public void setmCompServiceNumber(String mCompServiceNumber) {
		this.mCompServiceNumber = mCompServiceNumber;
	}
	public String getDispCompanyName() {
		return dispCompanyName;
	}
	public void setDispCompanyName(String dispCompanyName) {
		this.dispCompanyName = dispCompanyName;
	}
	public String getDispOrgName() {
		return dispOrgName;
	}
	public void setDispOrgName(String dispOrgName) {
		this.dispOrgName = dispOrgName;
	}
	public Date getStmtGenDate() {
		return stmtGenDate;
	}
	public void setStmtGenDate(Date stmtGenDate) {
		this.stmtGenDate = stmtGenDate;
	}
	public String getNetGeneration() {
		return netGeneration;
	}
	public void setNetGeneration(String netGeneration) {
		this.netGeneration = netGeneration;
	}
	public String getTotslCharges() {
		return totslCharges;
	}
	public void setTotslCharges(String totslCharges) {
		this.totslCharges = totslCharges;
	}
	public String getStmtMonth() {
		return stmtMonth;
	}
	public void setStmtMonth(String stmtMonth) {
		this.stmtMonth = stmtMonth;
	}
	public String getStmtYear() {
		return stmtYear;
	}
	public void setStmtYear(String stmtYear) {
		this.stmtYear = stmtYear;
	}
	public String getmOrgId() {
		return mOrgId;
	}
	public void setmOrgId(String mOrgId) {
		this.mOrgId = mOrgId;
	}
	public String getIsCaptive() {
		return isCaptive;
	}
	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}
	public String getIsStb() {
		return isStb;
	}
	public void setIsStb(String isStb) {
		this.isStb = isStb;
	}
	public String getAuditRemarks() {
		return auditRemarks;
	}
	public void setAuditRemarks(String auditRemarks) {
		this.auditRemarks = auditRemarks;
	}
	public String getDeleteRemarks() {
		return deleteRemarks;
	}
	public void setDeleteRemarks(String deleteRemarks) {
		this.deleteRemarks = deleteRemarks;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public LocalDate getDeletedDt() {
		return deletedDt;
	}
	public void setDeletedDt(LocalDate deletedDt) {
		this.deletedDt = deletedDt;
	}
	public LocalDate getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDate createdDt) {
		this.createdDt = createdDt;
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
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String gettGenStmtId() {
		return tGenStmtId;
	}
	public void settGenStmtId(String tGenStmtId) {
		this.tGenStmtId = tGenStmtId;
	}
	public String getmCompanyId() {
		return mCompanyId;
	}
	public void setmCompanyId(String mCompanyId) {
		this.mCompanyId = mCompanyId;
	}
	public String getmCompServiceId() {
		return mCompServiceId;
	}
	public void setmCompServiceId(String mCompServiceId) {
		this.mCompServiceId = mCompServiceId;
	}
	public String getExcessUnitType() {
		return excessUnitType;
	}
	public void setExcessUnitType(String excessUnitType) {
		this.excessUnitType = excessUnitType;
	}
	public String getIsThirdParty() {
		return isThirdParty;
	}
	public void setIsThirdParty(String isThirdParty) {
		this.isThirdParty = isThirdParty;
	}
	public String getFlowTypeCode() {
		return flowTypeCode;
	}
	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}
	
	
	
	
	
	
}