package com.ss.oa.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.Table;


@Table(name="INT_ADJUSTED_CHARGE")
@Entity
public class EnergyAdjustedDataCharge {
	@Id
	private String id;
	@Column(name="energy_adjusted_data_id")
	private String energyAdjustedDataId;
	private String chargeCode;
	private String chargeDesc;
	private String chargeAmount;

	public EnergyAdjustedDataCharge() {
		super();
	}

	@Override
	public String toString() {
		return "EnergyAdjustedDataCharge [id=" + id + ", energyAdjustedDataId=" + energyAdjustedDataId + ", chargeCode="
				+ chargeCode + ", chargeDesc=" + chargeDesc + ", chargeAmount=" + chargeAmount + "]";
	}

	public EnergyAdjustedDataCharge(String id, String energyAdjustedDataId, String chargeCode, String chargeDesc,
			String chargeAmount) {
		super();
		this.id = id;
		this.energyAdjustedDataId = energyAdjustedDataId;
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeAmount = chargeAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnergyAdjustedDataId() {
		return energyAdjustedDataId;
	}

	public void setEnergyAdjustedDataId(String energyAdjustedDataId) {
		this.energyAdjustedDataId = energyAdjustedDataId;
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

	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	
}
