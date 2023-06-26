package com.ss.oashared.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "T_INVOICE_LINE")

public class InvoiceLine {
	
	@Id
	@Column(name="LINEID")
	private Integer lineId;
	@Column(name="T_INV_HDR_ID")
	private String tInvHdrId;
	@Column(name="ITEMNO")
	private String itemNo;
	@Column(name="CHARGECODE")
	private String chargeCode;
	@Column(name="ITEMNAME")
	private String itemName;
	@Column(name="ITEMCODE")
	private String itemCode;
	@Column(name="ITEMDESC")
	private String itemDesc;
	@Column(name="ITEMNOTES")
	private String itemNotes;
	@Column(name="ITEMUNITCODE")
	private Float itemUnitCode;
	@Column(name="ITEMUNITNAME")
	private Float itemUnitName;
	@Column(name="ITEMRATE" ,columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal itemRate;
	@Column(name="ITEMBEFORETAXAMT", columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal itemBeforeTaxAmt;
	@Column(name="HASGST")
	private String hasGst;
	@Column(name="IGSTAXPER")
	private Float igstTaxPer;
	@Column(name="CGSTAXPER")
	private Float cgstTaxPer;
	@Column(name="SGSTAXPER")
	private Float sgstTaxPer;
	@Column(name="IGSTAXAMT",columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal igstTaxAmt;
	@Column(name="CGSTAXAMT",columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal cgstTaxAmt;
	@Column(name="SGSTAXAMT",columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal sgstTaxAmt;
	@Column(name="ITEMTAXAMT",columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal itemTaxAmt;
	@Column(name="ITEMTOTALAMT",columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal itemTotalAmt;
	@Column(name="ITEMINVHDRID")
	private String itemInvHdrId;
	@Column(name="ITEMINVLINEID")
	private String itemInvLineId;
	@Column(name="CREATEDBY")
	private String createdBy;
	@Column(name="CREATEDDATE")
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	@Column(name="MODIFIEDBY")
	private String modifiedBy;
	@Column(name="MODIFIEDDATE")
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	@Column(name="ITEMUNITCOUNT")
	private Float itemunitcount;
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String gettInvHdrId() {
		return tInvHdrId;
	}
	public void settInvHdrId(String tInvHdrId) {
		this.tInvHdrId = tInvHdrId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getItemNotes() {
		return itemNotes;
	}
	public void setItemNotes(String itemNotes) {
		this.itemNotes = itemNotes;
	}
	public Float getItemUnitCode() {
		return itemUnitCode;
	}
	public void setItemUnitCode(Float itemUnitCode) {
		this.itemUnitCode = itemUnitCode;
	}
	public Float getItemUnitName() {
		return itemUnitName;
	}
	public void setItemUnitName(Float itemUnitName) {
		this.itemUnitName = itemUnitName;
	}
	public BigDecimal getItemRate() {
		return itemRate;
	}
	public void setItemRate(BigDecimal itemRate) {
		this.itemRate = itemRate;
	}
	public BigDecimal getItemBeforeTaxAmt() {
		return itemBeforeTaxAmt;
	}
	public void setItemBeforeTaxAmt(BigDecimal itemBeforeTaxAmt) {
		this.itemBeforeTaxAmt = itemBeforeTaxAmt;
	}
	public String getHasGst() {
		return hasGst;
	}
	public void setHasGst(String hasGst) {
		this.hasGst = hasGst;
	}
	public Float getIgstTaxPer() {
		return igstTaxPer;
	}
	public void setIgstTaxPer(Float igstTaxPer) {
		this.igstTaxPer = igstTaxPer;
	}
	public Float getCgstTaxPer() {
		return cgstTaxPer;
	}
	public void setCgstTaxPer(Float cgstTaxPer) {
		this.cgstTaxPer = cgstTaxPer;
	}
	public Float getSgstTaxPer() {
		return sgstTaxPer;
	}
	public void setSgstTaxPer(Float sgstTaxPer) {
		this.sgstTaxPer = sgstTaxPer;
	}
	public BigDecimal getIgstTaxAmt() {
		return igstTaxAmt;
	}
	public void setIgstTaxAmt(BigDecimal igstTaxAmt) {
		this.igstTaxAmt = igstTaxAmt;
	}
	public BigDecimal getCgstTaxAmt() {
		return cgstTaxAmt;
	}
	public void setCgstTaxAmt(BigDecimal cgstTaxAmt) {
		this.cgstTaxAmt = cgstTaxAmt;
	}
	public BigDecimal getSgstTaxAmt() {
		return sgstTaxAmt;
	}
	public void setSgstTaxAmt(BigDecimal sgstTaxAmt) {
		this.sgstTaxAmt = sgstTaxAmt;
	}
	public BigDecimal getItemTaxAmt() {
		return itemTaxAmt;
	}
	public void setItemTaxAmt(BigDecimal itemTaxAmt) {
		this.itemTaxAmt = itemTaxAmt;
	}
	public BigDecimal getItemTotalAmt() {
		return itemTotalAmt;
	}
	public void setItemTotalAmt(BigDecimal itemTotalAmt) {
		this.itemTotalAmt = itemTotalAmt;
	}
	public String getItemInvHdrId() {
		return itemInvHdrId;
	}
	public void setItemInvHdrId(String itemInvHdrId) {
		this.itemInvHdrId = itemInvHdrId;
	}
	public String getItemInvLineId() {
		return itemInvLineId;
	}
	public void setItemInvLineId(String itemInvLineId) {
		this.itemInvLineId = itemInvLineId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Float getItemunitcount() {
		return itemunitcount;
	}
	public void setItemunitcount(Float itemunitcount) {
		this.itemunitcount = itemunitcount;
	}
	@Override
	public String toString() {
		return "InvoiceLine [lineId=" + lineId + ", tInvHdrId=" + tInvHdrId + ", itemNo=" + itemNo + ", chargeCode="
				+ chargeCode + ", itemName=" + itemName + ", itemCode=" + itemCode + ", itemDesc=" + itemDesc
				+ ", itemNotes=" + itemNotes + ", itemUnitCode=" + itemUnitCode + ", itemUnitName=" + itemUnitName
				+ ", itemRate=" + itemRate + ", itemBeforeTaxAmt=" + itemBeforeTaxAmt + ", hasGst=" + hasGst
				+ ", igstTaxPer=" + igstTaxPer + ", cgstTaxPer=" + cgstTaxPer + ", sgstTaxPer=" + sgstTaxPer
				+ ", igstTaxAmt=" + igstTaxAmt + ", cgstTaxAmt=" + cgstTaxAmt + ", sgstTaxAmt=" + sgstTaxAmt
				+ ", itemTaxAmt=" + itemTaxAmt + ", itemTotalAmt=" + itemTotalAmt + ", itemInvHdrId=" + itemInvHdrId
				+ ", itemInvLineId=" + itemInvLineId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", itemunitcount=" + itemunitcount
				+ ", getLineId()=" + getLineId() + ", gettInvHdrId()=" + gettInvHdrId() + ", getItemNo()=" + getItemNo()
				+ ", getChargeCode()=" + getChargeCode() + ", getItemName()=" + getItemName() + ", getItemCode()="
				+ getItemCode() + ", getItemDesc()=" + getItemDesc() + ", getItemNotes()=" + getItemNotes()
				+ ", getItemUnitCode()=" + getItemUnitCode() + ", getItemUnitName()=" + getItemUnitName()
				+ ", getItemRate()=" + getItemRate() + ", getItemBeforeTaxAmt()=" + getItemBeforeTaxAmt()
				+ ", getHasGst()=" + getHasGst() + ", getIgstTaxPer()=" + getIgstTaxPer() + ", getCgstTaxPer()="
				+ getCgstTaxPer() + ", getSgstTaxPer()=" + getSgstTaxPer() + ", getIgstTaxAmt()=" + getIgstTaxAmt()
				+ ", getCgstTaxAmt()=" + getCgstTaxAmt() + ", getSgstTaxAmt()=" + getSgstTaxAmt() + ", getItemTaxAmt()="
				+ getItemTaxAmt() + ", getItemTotalAmt()=" + getItemTotalAmt() + ", getItemInvHdrId()="
				+ getItemInvHdrId() + ", getItemInvLineId()=" + getItemInvLineId() + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedDate()=" + getCreatedDate() + ", getModifiedBy()=" + getModifiedBy()
				+ ", getModifiedDate()=" + getModifiedDate() + ", getItemunitcount()=" + getItemunitcount()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public InvoiceLine(Integer lineId, String tInvHdrId, String itemNo, String chargeCode, String itemName,
			String itemCode, String itemDesc, String itemNotes, Float itemUnitCode, Float itemUnitName,
			BigDecimal itemRate, BigDecimal itemBeforeTaxAmt, String hasGst, Float igstTaxPer, Float cgstTaxPer,
			Float sgstTaxPer, BigDecimal igstTaxAmt, BigDecimal cgstTaxAmt, BigDecimal sgstTaxAmt,
			BigDecimal itemTaxAmt, BigDecimal itemTotalAmt, String itemInvHdrId, String itemInvLineId, String createdBy,
			LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate, Float itemunitcount) {
		super();
		this.lineId = lineId;
		this.tInvHdrId = tInvHdrId;
		this.itemNo = itemNo;
		this.chargeCode = chargeCode;
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.itemDesc = itemDesc;
		this.itemNotes = itemNotes;
		this.itemUnitCode = itemUnitCode;
		this.itemUnitName = itemUnitName;
		this.itemRate = itemRate;
		this.itemBeforeTaxAmt = itemBeforeTaxAmt;
		this.hasGst = hasGst;
		this.igstTaxPer = igstTaxPer;
		this.cgstTaxPer = cgstTaxPer;
		this.sgstTaxPer = sgstTaxPer;
		this.igstTaxAmt = igstTaxAmt;
		this.cgstTaxAmt = cgstTaxAmt;
		this.sgstTaxAmt = sgstTaxAmt;
		this.itemTaxAmt = itemTaxAmt;
		this.itemTotalAmt = itemTotalAmt;
		this.itemInvHdrId = itemInvHdrId;
		this.itemInvLineId = itemInvLineId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.itemunitcount = itemunitcount;
	}
	public InvoiceLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	

		
}