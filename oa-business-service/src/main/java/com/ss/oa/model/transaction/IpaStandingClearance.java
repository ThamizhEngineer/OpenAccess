package com.ss.oa.model.transaction;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="T_IPA_STANDING_CLEARACE")
@CreationTimestamp @UpdateTimestamp
@Entity
@Scope("prototype")
public class IpaStandingClearance {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Column(name = "T_IPA_ID") 
	private String ipaId;
	@Column(name = "BUYER_COMP_SERV_ID") 
	private String buyerCompServId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDt;
	@Column(name = "T_IEX_NOC_ID") 
	private String iexNocId;
	@Column(name = "T_SC_ID") 
	private String scId;
	private String nocIexStatus;
	private String scStatus;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String importRemarks;
	private String quantum;
	
	public IpaStandingClearance() {
		super();
	}

	public IpaStandingClearance(String id, String ipaId, String buyerCompServId, LocalDate fromDt, LocalDate toDt,
			String iexNocId, String scId, String nocIexStatus, String scStatus, LocalDate createdDate,
			String importRemarks, String quantum) {
		super();
		this.id = id;
		this.ipaId = ipaId;
		this.buyerCompServId = buyerCompServId;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.iexNocId = iexNocId;
		this.scId = scId;
		this.nocIexStatus = nocIexStatus;
		this.scStatus = scStatus;
		this.createdDate = createdDate;
		this.importRemarks = importRemarks;
		this.quantum = quantum;
	}

	@Override
	public String toString() {
		return "IpaStandingClearance [id=" + id + ", ipaId=" + ipaId + ", buyerCompServId=" + buyerCompServId
				+ ", fromDt=" + fromDt + ", toDt=" + toDt + ", iexNocId=" + iexNocId + ", scId=" + scId
				+ ", nocIexStatus=" + nocIexStatus + ", scStatus=" + scStatus + ", createdDate=" + createdDate
				+ ", importRemarks=" + importRemarks + ", quantum=" + quantum + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpaId() {
		return ipaId;
	}

	public void setIpaId(String ipaId) {
		this.ipaId = ipaId;
	}

	public String getBuyerCompServId() {
		return buyerCompServId;
	}

	public void setBuyerCompServId(String buyerCompServId) {
		this.buyerCompServId = buyerCompServId;
	}

	public LocalDate getFromDt() {
		return fromDt;
	}

	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}

	public LocalDate getToDt() {
		return toDt;
	}

	public void setToDt(LocalDate toDt) {
		this.toDt = toDt;
	}

	public String getIexNocId() {
		return iexNocId;
	}

	public void setIexNocId(String iexNocId) {
		this.iexNocId = iexNocId;
	}

	public String getScId() {
		return scId;
	}

	public void setScId(String scId) {
		this.scId = scId;
	}

	public String getNocIexStatus() {
		return nocIexStatus;
	}

	public void setNocIexStatus(String nocIexStatus) {
		this.nocIexStatus = nocIexStatus;
	}

	public String getScStatus() {
		return scStatus;
	}

	public void setScStatus(String scStatus) {
		this.scStatus = scStatus;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getQuantum() {
		return quantum;
	}

	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
