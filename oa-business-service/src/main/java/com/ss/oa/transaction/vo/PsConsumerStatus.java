package com.ss.oa.transaction.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="V_PS_CONSUMER_STATUS")
@Component
public class PsConsumerStatus {
	@Id
	private String esLineId;
	private String esiCode;
	private String tEsId;
	private String nocId;
	private String nocStatus;
	private String nocBuyServId;
	private String consentId;
	private String consentStatus;
	private String consentBuyServId;
	private String buyerCompName;
	
	public PsConsumerStatus() {
		super();
	}

	public PsConsumerStatus(String esLineId, String esiCode, String tEsId, String nocId, String nocStatus,
			String nocBuyServId, String consentId, String consentStatus, String consentBuyServId,
			String buyerCompName) {
		super();
		this.esLineId = esLineId;
		this.esiCode = esiCode;
		this.tEsId = tEsId;
		this.nocId = nocId;
		this.nocStatus = nocStatus;
		this.nocBuyServId = nocBuyServId;
		this.consentId = consentId;
		this.consentStatus = consentStatus;
		this.consentBuyServId = consentBuyServId;
		this.buyerCompName = buyerCompName;
	}

	@Override
	public String toString() {
		return "PsConsumerStatus [esLineId=" + esLineId + ", esiCode=" + esiCode + ", tEsId=" + tEsId + ", nocId="
				+ nocId + ", nocStatus=" + nocStatus + ", nocBuyServId=" + nocBuyServId + ", consentId=" + consentId
				+ ", consentStatus=" + consentStatus + ", consentBuyServId=" + consentBuyServId + ", buyerCompName="
				+ buyerCompName + "]";
	}

	public String getEsLineId() {
		return esLineId;
	}

	public void setEsLineId(String esLineId) {
		this.esLineId = esLineId;
	}

	public String getEsiCode() {
		return esiCode;
	}

	public void setEsiCode(String esiCode) {
		this.esiCode = esiCode;
	}

	public String gettEsId() {
		return tEsId;
	}

	public void settEsId(String tEsId) {
		this.tEsId = tEsId;
	}

	public String getNocId() {
		return nocId;
	}

	public void setNocId(String nocId) {
		this.nocId = nocId;
	}

	public String getNocStatus() {
		return nocStatus;
	}

	public void setNocStatus(String nocStatus) {
		this.nocStatus = nocStatus;
	}

	public String getNocBuyServId() {
		return nocBuyServId;
	}

	public void setNocBuyServId(String nocBuyServId) {
		this.nocBuyServId = nocBuyServId;
	}

	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public String getConsentStatus() {
		return consentStatus;
	}

	public void setConsentStatus(String consentStatus) {
		this.consentStatus = consentStatus;
	}

	public String getConsentBuyServId() {
		return consentBuyServId;
	}

	public void setConsentBuyServId(String consentBuyServId) {
		this.consentBuyServId = consentBuyServId;
	}

	public String getBuyerCompName() {
		return buyerCompName;
	}

	public void setBuyerCompName(String buyerCompName) {
		this.buyerCompName = buyerCompName;
	}

}
