package com.ss.oa.reportService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="V_BUYER_AGREEMENT")
public class BuyerAgreement {
	@Id
	private String id;
	@Column(name="SELLER_SERVICE_ID")
	private String sellerServiceId;
	private String buyerOrgName;
	private String buyerCompanyName;
	private String buyerServiceNumber;
	private String quantumAgreed;
	public BuyerAgreement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSellerServiceId() {
		return sellerServiceId;
	}

	public void setSellerServiceId(String sellerServiceId) {
		this.sellerServiceId = sellerServiceId;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public String getBuyerServiceNumber() {
		return buyerServiceNumber;
	}

	public void setBuyerServiceNumber(String buyerServiceNumber) {
		this.buyerServiceNumber = buyerServiceNumber;
	}

	public String getQuantumAgreed() {
		return quantumAgreed;
	}

	public void setQuantumAgreed(String quantumAgreed) {
		this.quantumAgreed = quantumAgreed;
	}

	@Override
	public String toString() {
		return "BuyerAgreement [id=" + id + ", sellerServiceId=" + sellerServiceId + ", buyerOrgName=" + buyerOrgName
				+ ", buyerCompanyName=" + buyerCompanyName + ", buyerServiceNumber=" + buyerServiceNumber
				+ ", quantumAgreed=" + quantumAgreed + "]";
	}

	public BuyerAgreement(String id, String sellerServiceId, String buyerOrgName, String buyerCompanyName,
			String buyerServiceNumber, String quantumAgreed) {
		super();
		this.id = id;
		this.sellerServiceId = sellerServiceId;
		this.buyerOrgName = buyerOrgName;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerServiceNumber = buyerServiceNumber;
		this.quantumAgreed = quantumAgreed;
	}



	

}
