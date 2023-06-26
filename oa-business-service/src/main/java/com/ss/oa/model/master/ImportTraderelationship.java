package com.ss.oa.model.master;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="IMPORT_TRADE_REL")
@CreationTimestamp @UpdateTimestamp
@Entity
@Scope("prototype")
public class ImportTraderelationship implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String id;
	
	private String mSignupId;
	
	private String sellerCompanyName;
	
	private String sellerCompanyServiceNo;
	
	private String sellerEndEdc;
	
	private String quantum;
	
	private String fromDateStr;
	
	private String toDateStr;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	
	private String statusCode;
	
	private String buyerCompanyName;
	
	private String buyerEndEdc;
	
	private String buyerCompanyServiceNo;
	
	private String mBuyerCompanyId;
	
	private String mBuyerCompServiceId;
	
	private String remarks;
	
	private String flowTypeCode;
	
	private String createdBy;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	
	private String c1;
	
	private String c2;
	
	private String c3;
	
	private String c4;
	
	private String c5;
	
	private char enabled;
	
	private String peakUnits;
	
	private String offPeakUnits;
	
	private String intervalTypeCode;
	
	private String intervalTypeName;
	
	private String sharePercent;
	
	private String importRemarks;
	
	private String result;
	
	private String resultDesc;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate AgmtDt;
	

	public ImportTraderelationship() {
		super();
	}


	public ImportTraderelationship(String id, String sellerCompanyServiceNo, String quantum, LocalDate fromDate,
			LocalDate toDate, String buyerCompanyServiceNo, String statusCode, String sharePercent,
			String importRemarks) {
		super();
		this.id = id;
		this.sellerCompanyServiceNo = sellerCompanyServiceNo;
		this.quantum = quantum;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.buyerCompanyServiceNo = buyerCompanyServiceNo;
		this.statusCode = statusCode;
		this.sharePercent = sharePercent;
		this.importRemarks = importRemarks;
	}


	@Override
	public String toString() {
		return "ImportTraderelationship [id=" + id + ", sellerCompanyServiceNo=" + sellerCompanyServiceNo + ", quantum="
				+ quantum + ", fromDate=" + fromDate + ", toDate=" + toDate + ", buyerCompanyServiceNo="
				+ buyerCompanyServiceNo + ", statusCode=" + statusCode + ", sharePercent=" + sharePercent
				+ ", importRemarks=" + importRemarks + "]";
	}
	
	public String getmSignupId() {
		return mSignupId;
	}


	public void setmSignupId(String mSignupId) {
		this.mSignupId = mSignupId;
	}


	public String getSellerCompanyName() {
		return sellerCompanyName;
	}


	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}


	public String getSellerEndEdc() {
		return sellerEndEdc;
	}


	public void setSellerEndEdc(String sellerEndEdc) {
		this.sellerEndEdc = sellerEndEdc;
	}

	public String getFromDateStr() {
		return fromDateStr;
	}


	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}


	public String getToDateStr() {
		return toDateStr;
	}


	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}


	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}


	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}


	public String getBuyerEndEdc() {
		return buyerEndEdc;
	}


	public void setBuyerEndEdc(String buyerEndEdc) {
		this.buyerEndEdc = buyerEndEdc;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getSellerCompanyServiceNo() {
		return sellerCompanyServiceNo;
	}


	public void setSellerCompanyServiceNo(String sellerCompanyServiceNo) {
		this.sellerCompanyServiceNo = sellerCompanyServiceNo;
	}


	public String getQuantum() {
		return quantum;
	}


	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}


	public LocalDate getFromDate() {
		return fromDate;
	}


	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}


	public LocalDate getToDate() {
		return toDate;
	}


	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}	

	public String getmBuyerCompanyId() {
		return mBuyerCompanyId;
	}


	public void setmBuyerCompanyId(String mBuyerCompanyId) {
		this.mBuyerCompanyId = mBuyerCompanyId;
	}


	public String getmBuyerCompServiceId() {
		return mBuyerCompServiceId;
	}


	public void setmBuyerCompServiceId(String mBuyerCompServiceId) {
		this.mBuyerCompServiceId = mBuyerCompServiceId;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getFlowTypeCode() {
		return flowTypeCode;
	}


	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public String getC1() {
		return c1;
	}


	public void setC1(String c1) {
		this.c1 = c1;
	}


	public String getC2() {
		return c2;
	}


	public void setC2(String c2) {
		this.c2 = c2;
	}


	public String getC3() {
		return c3;
	}


	public void setC3(String c3) {
		this.c3 = c3;
	}


	public String getC4() {
		return c4;
	}


	public void setC4(String c4) {
		this.c4 = c4;
	}


	public String getC5() {
		return c5;
	}


	public void setC5(String c5) {
		this.c5 = c5;
	}


	public char getEnabled() {
		return enabled;
	}


	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}


	public String getPeakUnits() {
		return peakUnits;
	}


	public void setPeakUnits(String peakUnits) {
		this.peakUnits = peakUnits;
	}


	public String getOffPeakUnits() {
		return offPeakUnits;
	}


	public void setOffPeakUnits(String offPeakUnits) {
		this.offPeakUnits = offPeakUnits;
	}


	public String getIntervalTypeCode() {
		return intervalTypeCode;
	}


	public void setIntervalTypeCode(String intervalTypeCode) {
		this.intervalTypeCode = intervalTypeCode;
	}


	public String getIntervalTypeName() {
		return intervalTypeName;
	}


	public void setIntervalTypeName(String intervalTypeName) {
		this.intervalTypeName = intervalTypeName;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getResultDesc() {
		return resultDesc;
	}


	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}


	public LocalDate getAgmtDt() {
		return AgmtDt;
	}


	public void setAgmtDt(LocalDate agmtDt) {
		AgmtDt = agmtDt;
	}


	public String getBuyerCompanyServiceNo() {
		return buyerCompanyServiceNo;
	}


	public void setBuyerCompanyServiceNo(String buyerCompanyServiceNo) {
		this.buyerCompanyServiceNo = buyerCompanyServiceNo;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getSharePercent() {
		return sharePercent;
	}


	public void setSharePercent(String sharePercent) {
		this.sharePercent = sharePercent;
	}


	public String getImportRemarks() {
		return importRemarks;
	}


	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
