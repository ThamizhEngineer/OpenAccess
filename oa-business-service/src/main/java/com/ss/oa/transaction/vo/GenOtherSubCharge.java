package com.ss.oa.transaction.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="T_GEN_OTHER_SUB_CHARGES")
@CreationTimestamp	@UpdateTimestamp
public class GenOtherSubCharge {
	@Id
private String id;
@Column(name="T_GEN_OTHER_CHARGE_ID")
private String tGenOtherChargeId;
private String chargeCode;
private String chargeDesc;
private String chargeTypeCode;
private String unitCharge;
private String totalCharge;
private String remarks;
private String enabled;
private String createdBy;
@CreationTimestamp
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime createdDt;
private String modifiedBy;
public GenOtherSubCharge() {
	super();
	// TODO Auto-generated constructor stub
}
public GenOtherSubCharge(String id, String tGenOtherChargeId, String chargeCode, String chargeDesc,
		String chargeTypeCode, String unitCharge, String totalCharge, String remarks, String enabled, String createdBy,
		LocalDateTime createdDt, String modifiedBy) {
	super();
	this.id = id;
	this.tGenOtherChargeId = tGenOtherChargeId;
	this.chargeCode = chargeCode;
	this.chargeDesc = chargeDesc;
	this.chargeTypeCode = chargeTypeCode;
	this.unitCharge = unitCharge;
	this.totalCharge = totalCharge;
	this.remarks = remarks;
	this.enabled = enabled;
	this.createdBy = createdBy;
	this.createdDt = createdDt;
	this.modifiedBy = modifiedBy;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String gettGenOtherChargeId() {
	return tGenOtherChargeId;
}
public void settGenOtherChargeId(String tGenOtherChargeId) {
	this.tGenOtherChargeId = tGenOtherChargeId;
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
public String getTotalCharge() {
	return totalCharge;
}
public void setTotalCharge(String totalCharge) {
	this.totalCharge = totalCharge;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
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
public LocalDateTime getCreatedDt() {
	return createdDt;
}
public void setCreatedDt(LocalDateTime createdDt) {
	this.createdDt = createdDt;
}
public String getModifiedBy() {
	return modifiedBy;
}
public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}
@Override
public String toString() {
	return "GenOtherSubCharge [id=" + id + ", tGenOtherChargeId=" + tGenOtherChargeId + ", chargeCode=" + chargeCode
			+ ", chargeDesc=" + chargeDesc + ", chargeTypeCode=" + chargeTypeCode + ", unitCharge=" + unitCharge
			+ ", totalCharge=" + totalCharge + ", remarks=" + remarks + ", enabled=" + enabled + ", createdBy="
			+ createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + "]";
}


}
