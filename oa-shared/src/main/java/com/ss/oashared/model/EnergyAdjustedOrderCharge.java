package com.ss.oashared.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="ENERGY_ADJ_ORDER_CHARGE")
@Entity
public class EnergyAdjustedOrderCharge {
	@Id
	@Column(name="ROW_NO")
	public Integer id;
	public String suplrCode;
	public String serviceNo;
	public String readingMnth;
	public String readingYr;
	public String meterReadingCharges;
	@Column(name="O_M_CHARGES")
	public String oMCharges;
	public String systemOperationCharges;
	public String rkvahPenalty;
	public String negativeEnergyCharges;
	public String schedulingCharges;
	public String transmissionCharges;
	
	public EnergyAdjustedOrderCharge() {
		super();
	}

	public EnergyAdjustedOrderCharge(Integer id, String suplrCode, String serviceNo, String readingMnth,
			String readingYr, String meterReadingCharges, String oMCharges, String systemOperationCharges,
			String rkvahPenalty, String negativeEnergyCharges, String schedulingCharges, String transmissionCharges) {
		super();
		this.id = id;
		this.suplrCode = suplrCode;
		this.serviceNo = serviceNo;
		this.readingMnth = readingMnth;
		this.readingYr = readingYr;
		this.meterReadingCharges = meterReadingCharges;
		this.oMCharges = oMCharges;
		this.systemOperationCharges = systemOperationCharges;
		this.rkvahPenalty = rkvahPenalty;
		this.negativeEnergyCharges = negativeEnergyCharges;
		this.schedulingCharges = schedulingCharges;
		this.transmissionCharges = transmissionCharges;
	}

	@Override
	public String toString() {
		return "EnergyAdjustedOrderCharge [id=" + id + ", suplrCode=" + suplrCode + ", serviceNo=" + serviceNo
				+ ", readingMnth=" + readingMnth + ", readingYr=" + readingYr + ", meterReadingCharges="
				+ meterReadingCharges + ", oMCharges=" + oMCharges + ", systemOperationCharges="
				+ systemOperationCharges + ", rkvahPenalty=" + rkvahPenalty + ", negativeEnergyCharges="
				+ negativeEnergyCharges + ", schedulingCharges=" + schedulingCharges + ", transmissionCharges="
				+ transmissionCharges + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSuplrCode() {
		return suplrCode;
	}

	public void setSuplrCode(String suplrCode) {
		this.suplrCode = suplrCode;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getReadingMnth() {
		return readingMnth;
	}

	public void setReadingMnth(String readingMnth) {
		this.readingMnth = readingMnth;
	}

	public String getReadingYr() {
		return readingYr;
	}

	public void setReadingYr(String readingYr) {
		this.readingYr = readingYr;
	}

	public String getMeterReadingCharges() {
		return meterReadingCharges;
	}

	public void setMeterReadingCharges(String meterReadingCharges) {
		this.meterReadingCharges = meterReadingCharges;
	}

	public String getoMCharges() {
		return oMCharges;
	}

	public void setoMCharges(String oMCharges) {
		this.oMCharges = oMCharges;
	}

	public String getSystemOperationCharges() {
		return systemOperationCharges;
	}

	public void setSystemOperationCharges(String systemOperationCharges) {
		this.systemOperationCharges = systemOperationCharges;
	}

	public String getRkvahPenalty() {
		return rkvahPenalty;
	}

	public void setRkvahPenalty(String rkvahPenalty) {
		this.rkvahPenalty = rkvahPenalty;
	}

	public String getNegativeEnergyCharges() {
		return negativeEnergyCharges;
	}

	public void setNegativeEnergyCharges(String negativeEnergyCharges) {
		this.negativeEnergyCharges = negativeEnergyCharges;
	}

	public String getSchedulingCharges() {
		return schedulingCharges;
	}

	public void setSchedulingCharges(String schedulingCharges) {
		this.schedulingCharges = schedulingCharges;
	}

	public String getTransmissionCharges() {
		return transmissionCharges;
	}

	public void setTransmissionCharges(String transmissionCharges) {
		this.transmissionCharges = transmissionCharges;
	}
	
	
	

}
