package com.ss.oa.transaction.jpamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_GEN_STMT_SLOT")
public class GenerationStatementSlotJpa {
	@Id
	@Column(name="ID")
	private String id;
	private String remarks;
	@Column(name="T_GEN_STMT_ID")
	private String tGenStmtId;
	private String refNumber;
	private String slotCode;
	@Transient
	private String slotName;
	private String impInit;
	private String impFinal;
	private String impDiff;
	private String impUnits;
	private String expInit;
	private String expFinal;
	private String expDiff;
	private String expUnits;
	private String bankedBalance;
	private String netUnits;
	
	public GenerationStatementSlotJpa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerationStatementSlotJpa(String id, String remarks, String tGenStmtId, String refNumber, String slotCode,
			String slotName, String impInit, String impFinal, String impDiff, String impUnits, String expInit,
			String expFinal, String expDiff, String expUnits, String bankedBalance, String netUnits) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.tGenStmtId = tGenStmtId;
		this.refNumber = refNumber;
		this.slotCode = slotCode;
		this.slotName = slotName;
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
	}

	@Override
	public String toString() {
		return "GenerationStatementSlotJpa [id=" + id + ", remarks=" + remarks + ", tGenStmtId=" + tGenStmtId
				+ ", refNumber=" + refNumber + ", slotCode=" + slotCode + ", slotName=" + slotName + ", impInit="
				+ impInit + ", impFinal=" + impFinal + ", impDiff=" + impDiff + ", impUnits=" + impUnits + ", expInit="
				+ expInit + ", expFinal=" + expFinal + ", expDiff=" + expDiff + ", expUnits=" + expUnits
				+ ", bankedBalance=" + bankedBalance + ", netUnits=" + netUnits + "]";
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

	public String gettGenStmtId() {
		return tGenStmtId;
	}

	public void settGenStmtId(String tGenStmtId) {
		this.tGenStmtId = tGenStmtId;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getSlotCode() {
		return slotCode;
	}

	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
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

	
}
