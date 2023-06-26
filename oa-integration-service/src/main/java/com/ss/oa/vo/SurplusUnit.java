package com.ss.oa.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "INT_SURPLUS_UNIT")
@CreationTimestamp
@UpdateTimestamp
@Entity
public class SurplusUnit{
	@Id
	private String id;
	private String batchKey;
	private String serviceNo;
	private String suplrCode;
	private String suplrType;
	private String suplrName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate readingDt;
	private String readingMnth;
	private String readingYr;
	private String c24;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String buyerCompanyId;
	private String buyerCompanyServiceId;
	private String sellerCompanyId;
	private String sellerCompanyServiceId;
	private String fuelTypeCode;
	private String importRemarks;
	private String source;
    @Column(columnDefinition="CHAR")
	private String imported;
	
	public SurplusUnit() {
		super();
	}

	public SurplusUnit(String id, String batchKey, String serviceNo, String suplrCode, String suplrType,
			String suplrName, LocalDate readingDt, String readingMnth, String readingYr, String c24, String c1, String c2,
			String c3, String c4, String c5, String createdBy, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt, String buyerCompanyId, String buyerCompanyServiceId, String sellerCompanyId,
			String sellerCompanyServiceId, String fuelTypeCode, String importRemarks, String source, String imported) {
		super();
		this.id = id;
		this.batchKey = batchKey;
		this.serviceNo = serviceNo;
		this.suplrCode = suplrCode;
		this.suplrType = suplrType;
		this.suplrName = suplrName;
		this.readingDt = readingDt;
		this.readingMnth = readingMnth;
		this.readingYr = readingYr;
		this.c24 = c24;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.fuelTypeCode = fuelTypeCode;
		this.importRemarks = importRemarks;
		this.source = source;
		this.imported = imported;
	}

	@Override
	public String toString() {
		return "SurplusUnit [id=" + id + ", batchKey=" + batchKey + ", serviceNo=" + serviceNo + ", suplrCode="
				+ suplrCode + ", suplrType=" + suplrType + ", suplrName=" + suplrName + ", readingDt=" + readingDt
				+ ", readingMnth=" + readingMnth + ", readingYr=" + readingYr + ", c24=" + c24 + ", c1=" + c1 + ", c2="
				+ c2 + ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", createdBy=" + createdBy + ", createdDt="
				+ createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + ", buyerCompanyId="
				+ buyerCompanyId + ", buyerCompanyServiceId=" + buyerCompanyServiceId + ", sellerCompanyId="
				+ sellerCompanyId + ", sellerCompanyServiceId=" + sellerCompanyServiceId + ", fuelTypeCode="
				+ fuelTypeCode + ", importRemarks=" + importRemarks + ", source=" + source + ", imported=" + imported
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBatchKey() {
		return batchKey;
	}

	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getSuplrCode() {
		return suplrCode;
	}

	public void setSuplrCode(String suplrCode) {
		this.suplrCode = suplrCode;
	}

	public String getSuplrType() {
		return suplrType;
	}

	public void setSuplrType(String suplrType) {
		this.suplrType = suplrType;
	}

	public String getSuplrName() {
		return suplrName;
	}

	public void setSuplrName(String suplrName) {
		this.suplrName = suplrName;
	}

	public LocalDate getReadingDt() {
		return readingDt;
	}

	public void setReadingDt(LocalDate localDate) {
		this.readingDt = localDate;
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

	public String getC24() {
		return c24;
	}

	public void setC24(String c24) {
		this.c24 = c24;
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

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}

	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}

	public String getSellerCompanyId() {
		return sellerCompanyId;
	}

	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}

	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}

	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImported() {
		return imported;
	}

	public void setImported(String imported) {
		this.imported = imported;
	}


}
