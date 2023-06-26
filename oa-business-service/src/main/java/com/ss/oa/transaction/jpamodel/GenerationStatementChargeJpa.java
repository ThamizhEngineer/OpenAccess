package com.ss.oa.transaction.jpamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_GEN_STMT_CHARGE")
public class GenerationStatementChargeJpa {
	@Id
	@Column(name="ID")
	private String id;
	private String remarks;
	@Column(name="T_GEN_STMT_ID")
	private String tGenStmtId;
	private String chargeCode;
	private String chargeDesc;
	private String chargeTypeCode;
	@Transient
	private String chargeTypeName;
	private String unitCharge;
	private String totalCharges;
	
	public GenerationStatementChargeJpa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerationStatementChargeJpa(String id, String remarks, String tGenStmtId, String chargeCode,
			String chargeDesc, String chargeTypeCode, String chargeTypeName, String unitCharge, String totalCharges) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.tGenStmtId = tGenStmtId;
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeTypeCode = chargeTypeCode;
		this.chargeTypeName = chargeTypeName;
		this.unitCharge = unitCharge;
		this.totalCharges = totalCharges;
	}

	@Override
	public String toString() {
		return "GenerationStatementChargeJpa [id=" + id + ", remarks=" + remarks + ", tGenStmtId=" + tGenStmtId
				+ ", chargeCode=" + chargeCode + ", chargeDesc=" + chargeDesc + ", chargeTypeCode=" + chargeTypeCode
				+ ", chargeTypeName=" + chargeTypeName + ", unitCharge=" + unitCharge + ", totalCharges=" + totalCharges
				+ "]";
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

	public String getChargeTypeName() {
		return chargeTypeName;
	}

	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
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

	
	

}
