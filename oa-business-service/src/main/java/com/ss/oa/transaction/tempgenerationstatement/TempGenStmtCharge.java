package com.ss.oa.transaction.tempgenerationstatement;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="TMP_GEN_STMT_CHARGE")
@CreationTimestamp @UpdateTimestamp
@Entity
public class TempGenStmtCharge {
	@Id
	private String id;
	private String remarks;
@Column(name="TMP_GEN_STMT_ID")
	private String tmpGenStmtId;
	private String chargeCode;
	private String chargeDesc;
	private String chargeTypeCode;
	private String unitCharge;
	private String totalCharges;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	private char enabled;
	private String importRemarks;
	public TempGenStmtCharge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TempGenStmtCharge(String id, String remarks, String tmpGenStmtId, String chargeCode, String chargeDesc,
			String chargeTypeCode, String unitCharge, String totalCharges, LocalDateTime createdDate, char enabled,
			String importRemarks) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.tmpGenStmtId = tmpGenStmtId;
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeTypeCode = chargeTypeCode;
		this.unitCharge = unitCharge;
		this.totalCharges = totalCharges;
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
	public String getTmpGenStmtId() {
		return tmpGenStmtId;
	}
	public void setTmpGenStmtId(String tmpGenStmtId) {
		this.tmpGenStmtId = tmpGenStmtId;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	public String getChargeDesc() {
		return chargeDesc;
	}
	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}
	public String getChargeTypeCode() {
		return chargeTypeCode;
	}
	public void setChargeTypeCode(String chargeTypeCode) {
		this.chargeTypeCode = chargeTypeCode;
	}
	public String getUnitCharge() {
		return unitCharge;
	}
	public void setUnitCharge(String unitCharge) {
		this.unitCharge = unitCharge;
	}
	public String getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(String totalCharges) {
		this.totalCharges = totalCharges;
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
		return "TempGenStmtCharge [id=" + id + ", remarks=" + remarks + ", tmpGenStmtId=" + tmpGenStmtId
				+ ", chargeCode=" + chargeCode + ", chargeDesc=" + chargeDesc + ", chargeTypeCode=" + chargeTypeCode
				+ ", unitCharge=" + unitCharge + ", totalCharges=" + totalCharges + ", createdDate=" + createdDate
				+ ", enabled=" + enabled + ", importRemarks=" + importRemarks + "]";
	}
	
	
}
