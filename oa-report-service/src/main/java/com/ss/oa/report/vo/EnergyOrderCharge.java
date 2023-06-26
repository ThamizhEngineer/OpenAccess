package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="ENERGY_ORDER_CHARGE_REPORT")
@Entity
public class EnergyOrderCharge {
	@Id
	@Column(name="ROW_NO")
	public Integer id;
	public String sellerServiceNumber;
	public String buyerServiceNumber;
	public String month;
	public String year;
	public String meterReadingCharges;
	@Column(name="O_M_CHARGES")
	public String oMCharges;
	public String systemOperationCharges;
	public String rkvahPenalty;
	public String negativeEnergyCharges;
	public String schedulingCharges;
	public String transmissionCharges;
	
	
	public EnergyOrderCharge() {
		super();
	}


	public EnergyOrderCharge(Integer id, String sellerServiceNumber, String buyerServiceNumber, String month,
			String year, String meterReadingCharges, String oMCharges, String systemOperationCharges,
			String rkvahPenalty, String negativeEnergyCharges, String schedulingCharges, String transmissionCharges) {
		super();
		this.id = id;
		this.sellerServiceNumber = sellerServiceNumber;
		this.buyerServiceNumber = buyerServiceNumber;
		this.month = month;
		this.year = year;
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
		return "EnergyOrderCharge [id=" + id + ", sellerServiceNumber=" + sellerServiceNumber + ", buyerServiceNumber="
				+ buyerServiceNumber + ", month=" + month + ", year=" + year + ", meterReadingCharges="
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


	public String getSellerServiceNumber() {
		return sellerServiceNumber;
	}


	public void setSellerServiceNumber(String sellerServiceNumber) {
		this.sellerServiceNumber = sellerServiceNumber;
	}


	public String getBuyerServiceNumber() {
		return buyerServiceNumber;
	}


	public void setBuyerServiceNumber(String buyerServiceNumber) {
		this.buyerServiceNumber = buyerServiceNumber;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
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
