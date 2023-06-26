package com.ss.oa.transaction.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Entity
@Table(name = "V_EXS_BALANCE")
@Getter
public class ExcessUnit {
	@Id
	@Column(name="V_ID", columnDefinition="VARCHAR2")
	private String id;
	private String excessUnitType;
	private String readingMonth;
	private String readingYear; 
	@Column(name="M_COMPANY_ID")
	private String companyId; 
	@Column(name="M_COMPANY_SERVICE_ID")
	private String companyServiceId; 
	@Column(name="M_COMPANY_SERVICE_NUM")
	private String companyServiceNum; 
	private String bankingServiceId; 
	private String bankingServiceNum;  
	@Column(name="OPEN_C1")
	private String openC1;   
	@Column(name="OPEN_C2")
	private String openC2;   
	@Column(name="OPEN_C3")
	private String openC3;   
	@Column(name="OPEN_C4")
	private String openC4;   
	@Column(name="OPEN_C5")
	private String openC5;
	private String openTotalUnits; 
	private String openRemarks; 
	private String openUpdateBy; 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate openUpdateDt;
	@Column(name="INCR_EA1_C1")
	private String incrEa1C1;
	@Column(name="INCR_EA1_C2")
	private String incrEa1C2; 
	@Column(name="INCR_EA1_C3")
	private String incrEa1C3; 
	@Column(name="INCR_EA1_C4")
	private String incrEa1C4; 
	@Column(name="INCR_EA1_C5")
	private String incrEa1C5; 
	@Column(name="INCR_EA1_TOTAL_UNITS")
	private String incrEa1TotalUnits; 
	@Column(name="INCR_EA1_REMARKS")
	private String incrEa1Remarks; 
	@Column(name="INCR_EA1_SRC_ID")
	private String incrEa1SrcId; 
	@Column(name="INCR_EA1_UPDATE_BY")
	private String incrEa1UpdateBy; 
	@Column(name="INCR_EA1_UPDATE_DT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate incrEa1UpdateDt;	
	@Column(name="DECR_EA1_C1")  
	private String decrEa1C1; 	
	@Column(name="DECR_EA1_C2")  
	private String decrEa1C2; 	
	@Column(name="DECR_EA1_C3")  
	private String decrEa1C3; 	
	@Column(name="DECR_EA1_C4")  
	private String decrEa1C4; 	
	@Column(name="DECR_EA1_C5")  
	private String decrEa1C5; 
	@Column(name="DECR_EA1_TOTAL_UNITS")
	private String decrEa1TotalUnits; 
	@Column(name="DECR_EA1_REMARKS")
	private String decrEa1Remarks; 
	@Column(name="DECR_EA1_SRC_ID")
	private String decrEa1SrcId; 
	@Column(name="DECR_EA1_UPDATE_BY")
	private String decrEa1UpdateBy; 
	@Column(name="DECR_EA1_UPDATE_DT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate decrEa1UpdateDt;  
	@Column(name="INCR_HT_C1") 
	private String incrHtC1;   
	@Column(name="INCR_HT_C2")
	private String incrHtC2;   
	@Column(name="INCR_HT_C3")
	private String incrHtC3;   
	@Column(name="INCR_HT_C4")
	private String incrHtC4;   
	@Column(name="INCR_HT_C5")
	private String incrHtC5;  
	@Column(name="INCR_HT_TOTAL_UNITS")
	private String incrHtTotalUnits; 
	@Column(name="INCR_HT_REMARKS")
	private String incrHtRemarks; 
	@Column(name="INCR_HT_SRC_ID")
	private String incrHtSrcId; 
	@Column(name="INCR_HT_UPDATE_BY")
	private String incrHtUpdateBy; 
	@Column(name="INCR_HT_UPDATE_DT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate incrHtUpdateDt;
	@Column(name="CURR_C1")  
	private String currC1; 
	@Column(name="CURR_C2")
	private String currC2; 
	@Column(name="CURR_C3")
	private String currC3; 
	@Column(name="CURR_C4")
	private String currC4; 
	@Column(name="CURR_C5")
	private String currC5; 
	private String currTotalUnits; 
	private String currRemarks;   
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate currUpdateDt;
	private String remarks;  
	private String createdBy; 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	private String modifiedBy; 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDt;
	private String enabled;
	
	public ExcessUnit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcessUnit(String id, String excessUnitType, String readingMonth, String readingYear, String companyId,
			String companyServiceId, String companyServiceNum, String bankingServiceId, String bankingServiceNum,
			String openC1, String openC2, String openC3, String openC4, String openC5, String openTotalUnits,
			String openRemarks, String openUpdateBy, LocalDate openUpdateDt, String incrEa1C1, String incrEa1C2,
			String incrEa1C3, String incrEa1C4, String incrEa1C5, String incrEa1TotalUnits, String incrEa1Remarks,
			String incrEa1SrcId, String incrEa1UpdateBy, LocalDate incrEa1UpdateDt, String decrEa1C1, String decrEa1C2,
			String decrEa1C3, String decrEa1C4, String decrEa1C5, String decrEa1TotalUnits, String decrEa1Remarks,
			String decrEa1SrcId, String decrEa1UpdateBy, LocalDate decrEa1UpdateDt, String incrHtC1, String incrHtC2,
			String incrHtC3, String incrHtC4, String incrHtC5, String incrHtTotalUnits, String incrHtRemarks,
			String incrHtSrcId, String incrHtUpdateBy, LocalDate incrHtUpdateDt, String currC1, String currC2,
			String currC3, String currC4, String currC5, String currTotalUnits, String currRemarks,
			LocalDate currUpdateDt, String remarks, String createdBy, LocalDate createdDt, String modifiedBy,
			LocalDate modifiedDt, String enabled) {
		super();
		this.id = id;
		this.excessUnitType = excessUnitType;
		this.readingMonth = readingMonth;
		this.readingYear = readingYear;
		this.companyId = companyId;
		this.companyServiceId = companyServiceId;
		this.companyServiceNum = companyServiceNum;
		this.bankingServiceId = bankingServiceId;
		this.bankingServiceNum = bankingServiceNum;
		this.openC1 = openC1;
		this.openC2 = openC2;
		this.openC3 = openC3;
		this.openC4 = openC4;
		this.openC5 = openC5;
		this.openTotalUnits = openTotalUnits;
		this.openRemarks = openRemarks;
		this.openUpdateBy = openUpdateBy;
		this.openUpdateDt = openUpdateDt;
		this.incrEa1C1 = incrEa1C1;
		this.incrEa1C2 = incrEa1C2;
		this.incrEa1C3 = incrEa1C3;
		this.incrEa1C4 = incrEa1C4;
		this.incrEa1C5 = incrEa1C5;
		this.incrEa1TotalUnits = incrEa1TotalUnits;
		this.incrEa1Remarks = incrEa1Remarks;
		this.incrEa1SrcId = incrEa1SrcId;
		this.incrEa1UpdateBy = incrEa1UpdateBy;
		this.incrEa1UpdateDt = incrEa1UpdateDt;
		this.decrEa1C1 = decrEa1C1;
		this.decrEa1C2 = decrEa1C2;
		this.decrEa1C3 = decrEa1C3;
		this.decrEa1C4 = decrEa1C4;
		this.decrEa1C5 = decrEa1C5;
		this.decrEa1TotalUnits = decrEa1TotalUnits;
		this.decrEa1Remarks = decrEa1Remarks;
		this.decrEa1SrcId = decrEa1SrcId;
		this.decrEa1UpdateBy = decrEa1UpdateBy;
		this.decrEa1UpdateDt = decrEa1UpdateDt;
		this.incrHtC1 = incrHtC1;
		this.incrHtC2 = incrHtC2;
		this.incrHtC3 = incrHtC3;
		this.incrHtC4 = incrHtC4;
		this.incrHtC5 = incrHtC5;
		this.incrHtTotalUnits = incrHtTotalUnits;
		this.incrHtRemarks = incrHtRemarks;
		this.incrHtSrcId = incrHtSrcId;
		this.incrHtUpdateBy = incrHtUpdateBy;
		this.incrHtUpdateDt = incrHtUpdateDt;
		this.currC1 = currC1;
		this.currC2 = currC2;
		this.currC3 = currC3;
		this.currC4 = currC4;
		this.currC5 = currC5;
		this.currTotalUnits = currTotalUnits;
		this.currRemarks = currRemarks;
		this.currUpdateDt = currUpdateDt;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "ExcessUnit [id=" + id + ", excessUnitType=" + excessUnitType + ", readingMonth=" + readingMonth
				+ ", readingYear=" + readingYear + ", companyId=" + companyId + ", companyServiceId=" + companyServiceId
				+ ", companyServiceNum=" + companyServiceNum + ", bankingServiceId=" + bankingServiceId
				+ ", bankingServiceNum=" + bankingServiceNum + ", openC1=" + openC1 + ", openC2=" + openC2 + ", openC3="
				+ openC3 + ", openC4=" + openC4 + ", openC5=" + openC5 + ", openTotalUnits=" + openTotalUnits
				+ ", openRemarks=" + openRemarks + ", openUpdateBy=" + openUpdateBy + ", openUpdateDt=" + openUpdateDt
				+ ", incrEa1C1=" + incrEa1C1 + ", incrEa1C2=" + incrEa1C2 + ", incrEa1C3=" + incrEa1C3 + ", incrEa1C4="
				+ incrEa1C4 + ", incrEa1C5=" + incrEa1C5 + ", incrEa1TotalUnits=" + incrEa1TotalUnits
				+ ", incrEa1Remarks=" + incrEa1Remarks + ", incrEa1SrcId=" + incrEa1SrcId + ", incrEa1UpdateBy="
				+ incrEa1UpdateBy + ", incrEa1UpdateDt=" + incrEa1UpdateDt + ", decrEa1C1=" + decrEa1C1 + ", decrEa1C2="
				+ decrEa1C2 + ", decrEa1C3=" + decrEa1C3 + ", decrEa1C4=" + decrEa1C4 + ", decrEa1C5=" + decrEa1C5
				+ ", decrEa1TotalUnits=" + decrEa1TotalUnits + ", decrEa1Remarks=" + decrEa1Remarks + ", decrEa1SrcId="
				+ decrEa1SrcId + ", decrEa1UpdateBy=" + decrEa1UpdateBy + ", decrEa1UpdateDt=" + decrEa1UpdateDt
				+ ", incrHtC1=" + incrHtC1 + ", incrHtC2=" + incrHtC2 + ", incrHtC3=" + incrHtC3 + ", incrHtC4="
				+ incrHtC4 + ", incrHtC5=" + incrHtC5 + ", incrHtTotalUnits=" + incrHtTotalUnits + ", incrHtRemarks="
				+ incrHtRemarks + ", incrHtSrcId=" + incrHtSrcId + ", incrHtUpdateBy=" + incrHtUpdateBy
				+ ", incrHtUpdateDt=" + incrHtUpdateDt + ", currC1=" + currC1 + ", currC2=" + currC2 + ", currC3="
				+ currC3 + ", currC4=" + currC4 + ", currC5=" + currC5 + ", currTotalUnits=" + currTotalUnits
				+ ", currRemarks=" + currRemarks + ", currUpdateDt=" + currUpdateDt + ", remarks=" + remarks
				+ ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + ", enabled=" + enabled + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExcessUnitType() {
		return excessUnitType;
	}

	public void setExcessUnitType(String excessUnitType) {
		this.excessUnitType = excessUnitType;
	}

	public String getReadingMonth() {
		return readingMonth;
	}

	public void setReadingMonth(String readingMonth) {
		this.readingMonth = readingMonth;
	}

	public String getReadingYear() {
		return readingYear;
	}

	public void setReadingYear(String readingYear) {
		this.readingYear = readingYear;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyServiceId() {
		return companyServiceId;
	}

	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}

	public String getCompanyServiceNum() {
		return companyServiceNum;
	}

	public void setCompanyServiceNum(String companyServiceNum) {
		this.companyServiceNum = companyServiceNum;
	}

	public String getBankingServiceId() {
		return bankingServiceId;
	}

	public void setBankingServiceId(String bankingServiceId) {
		this.bankingServiceId = bankingServiceId;
	}

	public String getBankingServiceNum() {
		return bankingServiceNum;
	}

	public void setBankingServiceNum(String bankingServiceNum) {
		this.bankingServiceNum = bankingServiceNum;
	}

	public String getOpenC1() {
		return openC1;
	}

	public void setOpenC1(String openC1) {
		this.openC1 = openC1;
	}

	public String getOpenC2() {
		return openC2;
	}

	public void setOpenC2(String openC2) {
		this.openC2 = openC2;
	}

	public String getOpenC3() {
		return openC3;
	}

	public void setOpenC3(String openC3) {
		this.openC3 = openC3;
	}

	public String getOpenC4() {
		return openC4;
	}

	public void setOpenC4(String openC4) {
		this.openC4 = openC4;
	}

	public String getOpenC5() {
		return openC5;
	}

	public void setOpenC5(String openC5) {
		this.openC5 = openC5;
	}

	public String getOpenTotalUnits() {
		return openTotalUnits;
	}

	public void setOpenTotalUnits(String openTotalUnits) {
		this.openTotalUnits = openTotalUnits;
	}

	public String getOpenRemarks() {
		return openRemarks;
	}

	public void setOpenRemarks(String openRemarks) {
		this.openRemarks = openRemarks;
	}

	public String getOpenUpdateBy() {
		return openUpdateBy;
	}

	public void setOpenUpdateBy(String openUpdateBy) {
		this.openUpdateBy = openUpdateBy;
	}

	public LocalDate getOpenUpdateDt() {
		return openUpdateDt;
	}

	public void setOpenUpdateDt(LocalDate openUpdateDt) {
		this.openUpdateDt = openUpdateDt;
	}

	public String getIncrEa1C1() {
		return incrEa1C1;
	}

	public void setIncrEa1C1(String incrEa1C1) {
		this.incrEa1C1 = incrEa1C1;
	}

	public String getIncrEa1C2() {
		return incrEa1C2;
	}

	public void setIncrEa1C2(String incrEa1C2) {
		this.incrEa1C2 = incrEa1C2;
	}

	public String getIncrEa1C3() {
		return incrEa1C3;
	}

	public void setIncrEa1C3(String incrEa1C3) {
		this.incrEa1C3 = incrEa1C3;
	}

	public String getIncrEa1C4() {
		return incrEa1C4;
	}

	public void setIncrEa1C4(String incrEa1C4) {
		this.incrEa1C4 = incrEa1C4;
	}

	public String getIncrEa1C5() {
		return incrEa1C5;
	}

	public void setIncrEa1C5(String incrEa1C5) {
		this.incrEa1C5 = incrEa1C5;
	}

	public String getIncrEa1TotalUnits() {
		return incrEa1TotalUnits;
	}

	public void setIncrEa1TotalUnits(String incrEa1TotalUnits) {
		this.incrEa1TotalUnits = incrEa1TotalUnits;
	}

	public String getIncrEa1Remarks() {
		return incrEa1Remarks;
	}

	public void setIncrEa1Remarks(String incrEa1Remarks) {
		this.incrEa1Remarks = incrEa1Remarks;
	}

	public String getIncrEa1SrcId() {
		return incrEa1SrcId;
	}

	public void setIncrEa1SrcId(String incrEa1SrcId) {
		this.incrEa1SrcId = incrEa1SrcId;
	}

	public String getIncrEa1UpdateBy() {
		return incrEa1UpdateBy;
	}

	public void setIncrEa1UpdateBy(String incrEa1UpdateBy) {
		this.incrEa1UpdateBy = incrEa1UpdateBy;
	}

	public LocalDate getIncrEa1UpdateDt() {
		return incrEa1UpdateDt;
	}

	public void setIncrEa1UpdateDt(LocalDate incrEa1UpdateDt) {
		this.incrEa1UpdateDt = incrEa1UpdateDt;
	}

	public String getDecrEa1C1() {
		return decrEa1C1;
	}

	public void setDecrEa1C1(String decrEa1C1) {
		this.decrEa1C1 = decrEa1C1;
	}

	public String getDecrEa1C2() {
		return decrEa1C2;
	}

	public void setDecrEa1C2(String decrEa1C2) {
		this.decrEa1C2 = decrEa1C2;
	}

	public String getDecrEa1C3() {
		return decrEa1C3;
	}

	public void setDecrEa1C3(String decrEa1C3) {
		this.decrEa1C3 = decrEa1C3;
	}

	public String getDecrEa1C4() {
		return decrEa1C4;
	}

	public void setDecrEa1C4(String decrEa1C4) {
		this.decrEa1C4 = decrEa1C4;
	}

	public String getDecrEa1C5() {
		return decrEa1C5;
	}

	public void setDecrEa1C5(String decrEa1C5) {
		this.decrEa1C5 = decrEa1C5;
	}

	public String getDecrEa1TotalUnits() {
		return decrEa1TotalUnits;
	}

	public void setDecrEa1TotalUnits(String decrEa1TotalUnits) {
		this.decrEa1TotalUnits = decrEa1TotalUnits;
	}

	public String getDecrEa1Remarks() {
		return decrEa1Remarks;
	}

	public void setDecrEa1Remarks(String decrEa1Remarks) {
		this.decrEa1Remarks = decrEa1Remarks;
	}

	public String getDecrEa1SrcId() {
		return decrEa1SrcId;
	}

	public void setDecrEa1SrcId(String decrEa1SrcId) {
		this.decrEa1SrcId = decrEa1SrcId;
	}

	public String getDecrEa1UpdateBy() {
		return decrEa1UpdateBy;
	}

	public void setDecrEa1UpdateBy(String decrEa1UpdateBy) {
		this.decrEa1UpdateBy = decrEa1UpdateBy;
	}

	public LocalDate getDecrEa1UpdateDt() {
		return decrEa1UpdateDt;
	}

	public void setDecrEa1UpdateDt(LocalDate decrEa1UpdateDt) {
		this.decrEa1UpdateDt = decrEa1UpdateDt;
	}

	public String getIncrHtC1() {
		return incrHtC1;
	}

	public void setIncrHtC1(String incrHtC1) {
		this.incrHtC1 = incrHtC1;
	}

	public String getIncrHtC2() {
		return incrHtC2;
	}

	public void setIncrHtC2(String incrHtC2) {
		this.incrHtC2 = incrHtC2;
	}

	public String getIncrHtC3() {
		return incrHtC3;
	}

	public void setIncrHtC3(String incrHtC3) {
		this.incrHtC3 = incrHtC3;
	}

	public String getIncrHtC4() {
		return incrHtC4;
	}

	public void setIncrHtC4(String incrHtC4) {
		this.incrHtC4 = incrHtC4;
	}

	public String getIncrHtC5() {
		return incrHtC5;
	}

	public void setIncrHtC5(String incrHtC5) {
		this.incrHtC5 = incrHtC5;
	}

	public String getIncrHtTotalUnits() {
		return incrHtTotalUnits;
	}

	public void setIncrHtTotalUnits(String incrHtTotalUnits) {
		this.incrHtTotalUnits = incrHtTotalUnits;
	}

	public String getIncrHtRemarks() {
		return incrHtRemarks;
	}

	public void setIncrHtRemarks(String incrHtRemarks) {
		this.incrHtRemarks = incrHtRemarks;
	}

	public String getIncrHtSrcId() {
		return incrHtSrcId;
	}

	public void setIncrHtSrcId(String incrHtSrcId) {
		this.incrHtSrcId = incrHtSrcId;
	}

	public String getIncrHtUpdateBy() {
		return incrHtUpdateBy;
	}

	public void setIncrHtUpdateBy(String incrHtUpdateBy) {
		this.incrHtUpdateBy = incrHtUpdateBy;
	}

	public LocalDate getIncrHtUpdateDt() {
		return incrHtUpdateDt;
	}

	public void setIncrHtUpdateDt(LocalDate incrHtUpdateDt) {
		this.incrHtUpdateDt = incrHtUpdateDt;
	}

	public String getCurrC1() {
		return currC1;
	}

	public void setCurrC1(String currC1) {
		this.currC1 = currC1;
	}

	public String getCurrC2() {
		return currC2;
	}

	public void setCurrC2(String currC2) {
		this.currC2 = currC2;
	}

	public String getCurrC3() {
		return currC3;
	}

	public void setCurrC3(String currC3) {
		this.currC3 = currC3;
	}

	public String getCurrC4() {
		return currC4;
	}

	public void setCurrC4(String currC4) {
		this.currC4 = currC4;
	}

	public String getCurrC5() {
		return currC5;
	}

	public void setCurrC5(String currC5) {
		this.currC5 = currC5;
	}

	public String getCurrTotalUnits() {
		return currTotalUnits;
	}

	public void setCurrTotalUnits(String currTotalUnits) {
		this.currTotalUnits = currTotalUnits;
	}

	public String getCurrRemarks() {
		return currRemarks;
	}

	public void setCurrRemarks(String currRemarks) {
		this.currRemarks = currRemarks;
	}

	public LocalDate getCurrUpdateDt() {
		return currUpdateDt;
	}

	public void setCurrUpdateDt(LocalDate currUpdateDt) {
		this.currUpdateDt = currUpdateDt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDate createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDate modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	
	
	
}
