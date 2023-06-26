package com.ss.oa.oadocumentservice.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TempGenStmtSlot {
	public String id;
	public String remarks;
	public String refNumber;
	public String tmpGenStmtId;
	public String slotCode;
	public String impInit;
	public String impFinal;
	public String impDiff;
	public String impUnits;
	public String expInit;
	public String expFinal;
	public String expDiff;
	public String expUnits;
	public String bankedBalance;
	public String netUnits;
	public String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime createdDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime createdDate;
	public char enabled;
	public String importRemarks;
	public TempGenStmtSlot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TempGenStmtSlot(String id, String remarks, String refNumber, String tmpGenStmtId, String slotCode,
			String impInit, String impFinal, String impDiff, String impUnits, String expInit, String expFinal,
			String expDiff, String expUnits, String bankedBalance, String netUnits, String createdBy,
			LocalDateTime createdDt, LocalDateTime createdDate, char enabled, String importRemarks) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.refNumber = refNumber;
		this.tmpGenStmtId = tmpGenStmtId;
		this.slotCode = slotCode;
		this.impInit = impInit;
		this.impFinal = impFinal;
		this.impDiff = impDiff;
		this.impUnits = impUnits;
		this.expInit = expInit;
		this.expFinal = expFinal;
		this.expDiff = expDiff;
		this.expUnits = expUnits;
		this.bankedBalance = bankedBalance;
		this.netUnits = netUnits;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.createdDate = createdDate;
		this.enabled = enabled;
		this.importRemarks = importRemarks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public String getTmpGenStmtId() {
		return tmpGenStmtId;
	}
	public void setTmpGenStmtId(String tmpGenStmtId) {
		this.tmpGenStmtId = tmpGenStmtId;
	}
	public String getSlotCode() {
		return slotCode;
	}
	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}
	public String getImpInit() {
		return impInit;
	}
	public void setImpInit(String impInit) {
		this.impInit = impInit;
	}
	public String getImpFinal() {
		return impFinal;
	}
	public void setImpFinal(String impFinal) {
		this.impFinal = impFinal;
	}
	public String getImpDiff() {
		return impDiff;
	}
	public void setImpDiff(String impDiff) {
		this.impDiff = impDiff;
	}
	public String getImpUnits() {
		return impUnits;
	}
	public void setImpUnits(String impUnits) {
		this.impUnits = impUnits;
	}
	public String getExpInit() {
		return expInit;
	}
	public void setExpInit(String expInit) {
		this.expInit = expInit;
	}
	public String getExpFinal() {
		return expFinal;
	}
	public void setExpFinal(String expFinal) {
		this.expFinal = expFinal;
	}
	public String getExpDiff() {
		return expDiff;
	}
	public void setExpDiff(String expDiff) {
		this.expDiff = expDiff;
	}
	public String getExpUnits() {
		return expUnits;
	}
	public void setExpUnits(String expUnits) {
		this.expUnits = expUnits;
	}
	public String getBankedBalance() {
		return bankedBalance;
	}
	public void setBankedBalance(String bankedBalance) {
		this.bankedBalance = bankedBalance;
	}
	public String getNetUnits() {
		return netUnits;
	}
	public void setNetUnits(String netUnits) {
		this.netUnits = netUnits;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public char getEnabled() {
		return enabled;
	}
	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}
	public String getImportRemarks() {
		return importRemarks;
	}
	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}
	@Override
	public String toString() {
		return "TempGenStmtSlot [id=" + id + ", remarks=" + remarks + ", refNumber=" + refNumber + ", tmpGenStmtId="
				+ tmpGenStmtId + ", slotCode=" + slotCode + ", impInit=" + impInit + ", impFinal=" + impFinal
				+ ", impDiff=" + impDiff + ", impUnits=" + impUnits + ", expInit=" + expInit + ", expFinal=" + expFinal
				+ ", expDiff=" + expDiff + ", expUnits=" + expUnits + ", bankedBalance=" + bankedBalance + ", netUnits="
				+ netUnits + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", createdDate=" + createdDate
				+ ", enabled=" + enabled + ", importRemarks=" + importRemarks + "]";
	}
	
	
	
}
