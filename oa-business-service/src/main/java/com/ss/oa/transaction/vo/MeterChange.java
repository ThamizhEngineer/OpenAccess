package com.ss.oa.transaction.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class MeterChange {
	
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  String id;
	private String ServicNo;
	private String OldMeterNo;
	private String NewMeterNo;
	private String ReadingMonth;
	private String ReadingYear;
	private String TotalImportUnits;
	private String TotalExportUnits;
	private String RkvahUnits;
	private String TotalNetUnits;
	private String Remarks;
	private String ImpC1Units;
	private String ImpC2Units;
	private String ImpC3Units;
	private String ImpC4Units;
	private String ImpC5Units;
	private String ExpC1Units;
	private String ExpC2Units;
	private String ExpC3Units;
	private String ExpC4Units;
	private String ExpC5Units;
	private String NetC1Units;
	private String NetC2Units;
	private String NetC3Units;
	private String NetC4Units;
	private String NetC5Units;
	private String ImportRemarks;
	private String CreatedBy;
	private String CreatedDate;
	private String ModifiedBy;
	private String ModifiedDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServicNo() {
		return ServicNo;
	}
	public void setServicNo(String servicNo) {
		ServicNo = servicNo;
	}
	public String getOldMeterNo() {
		return OldMeterNo;
	}
	public void setOldMeterNo(String oldMeterNo) {
		OldMeterNo = oldMeterNo;
	}
	public String getNewMeterNo() {
		return NewMeterNo;
	}
	public void setNewMeterNo(String newMeterNo) {
		NewMeterNo = newMeterNo;
	}
	public String getReadingMonth() {
		return ReadingMonth;
	}
	public void setReadingMonth(String readingMonth) {
		ReadingMonth = readingMonth;
	}
	public String getReadingYear() {
		return ReadingYear;
	}
	public void setReadingYear(String readingYear) {
		ReadingYear = readingYear;
	}
	public String getTotalImportUnits() {
		return TotalImportUnits;
	}
	public void setTotalImportUnits(String totalImportUnits) {
		TotalImportUnits = totalImportUnits;
	}
	public String getTotalExportUnits() {
		return TotalExportUnits;
	}
	public void setTotalExportUnits(String totalExportUnits) {
		TotalExportUnits = totalExportUnits;
	}
	public String getRkvahUnits() {
		return RkvahUnits;
	}
	public void setRkvahUnits(String rkvahUnits) {
		RkvahUnits = rkvahUnits;
	}
	public String getTotalNetUnits() {
		return TotalNetUnits;
	}
	public void setTotalNetUnits(String totalNetUnits) {
		TotalNetUnits = totalNetUnits;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getImpC1Units() {
		return ImpC1Units;
	}
	public void setImpC1Units(String impC1Units) {
		ImpC1Units = impC1Units;
	}
	public String getImpC2Units() {
		return ImpC2Units;
	}
	public void setImpC2Units(String impC2Units) {
		ImpC2Units = impC2Units;
	}
	public String getImpC3Units() {
		return ImpC3Units;
	}
	public void setImpC3Units(String impC3Units) {
		ImpC3Units = impC3Units;
	}
	public String getImpC4Units() {
		return ImpC4Units;
	}
	public void setImpC4Units(String impC4Units) {
		ImpC4Units = impC4Units;
	}
	public String getImpC5Units() {
		return ImpC5Units;
	}
	public void setImpC5Units(String impC5Units) {
		ImpC5Units = impC5Units;
	}
	public String getExpC1Units() {
		return ExpC1Units;
	}
	public void setExpC1Units(String expC1Units) {
		ExpC1Units = expC1Units;
	}
	public String getExpC2Units() {
		return ExpC2Units;
	}
	public void setExpC2Units(String expC2Units) {
		ExpC2Units = expC2Units;
	}
	public String getExpC3Units() {
		return ExpC3Units;
	}
	public void setExpC3Units(String expC3Units) {
		ExpC3Units = expC3Units;
	}
	public String getExpC4Units() {
		return ExpC4Units;
	}
	public void setExpC4Units(String expC4Units) {
		ExpC4Units = expC4Units;
	}
	public String getExpC5Units() {
		return ExpC5Units;
	}
	public void setExpC5Units(String expC5Units) {
		ExpC5Units = expC5Units;
	}
	public String getNetC1Units() {
		return NetC1Units;
	}
	public void setNetC1Units(String netC1Units) {
		NetC1Units = netC1Units;
	}
	public String getNetC2Units() {
		return NetC2Units;
	}
	public void setNetC2Units(String netC2Units) {
		NetC2Units = netC2Units;
	}
	public String getNetC3Units() {
		return NetC3Units;
	}
	public void setNetC3Units(String netC3Units) {
		NetC3Units = netC3Units;
	}
	public String getNetC4Units() {
		return NetC4Units;
	}
	public void setNetC4Units(String netC4Units) {
		NetC4Units = netC4Units;
	}
	public String getNetC5Units() {
		return NetC5Units;
	}
	public void setNetC5Units(String netC5Units) {
		NetC5Units = netC5Units;
	}
	public String getImportRemarks() {
		return ImportRemarks;
	}
	public void setImportRemarks(String importRemarks) {
		ImportRemarks = importRemarks;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "MeterChange [id=" + id + ", ServicNo=" + ServicNo + ", OldMeterNo=" + OldMeterNo + ", NewMeterNo="
				+ NewMeterNo + ", ReadingMonth=" + ReadingMonth + ", ReadingYear=" + ReadingYear + ", TotalImportUnits="
				+ TotalImportUnits + ", TotalExportUnits=" + TotalExportUnits + ", RkvahUnits=" + RkvahUnits
				+ ", TotalNetUnits=" + TotalNetUnits + ", Remarks=" + Remarks + ", ImpC1Units=" + ImpC1Units
				+ ", ImpC2Units=" + ImpC2Units + ", ImpC3Units=" + ImpC3Units + ", ImpC4Units=" + ImpC4Units
				+ ", ImpC5Units=" + ImpC5Units + ", ExpC1Units=" + ExpC1Units + ", ExpC2Units=" + ExpC2Units
				+ ", ExpC3Units=" + ExpC3Units + ", ExpC4Units=" + ExpC4Units + ", ExpC5Units=" + ExpC5Units
				+ ", NetC1Units=" + NetC1Units + ", NetC2Units=" + NetC2Units + ", NetC3Units=" + NetC3Units
				+ ", NetC4Units=" + NetC4Units + ", NetC5Units=" + NetC5Units + ", ImportRemarks=" + ImportRemarks
				+ ", CreatedBy=" + CreatedBy + ", CreatedDate=" + CreatedDate + ", ModifiedBy=" + ModifiedBy
				+ ", ModifiedDate=" + ModifiedDate + ", getId()=" + getId() + ", getServicNo()=" + getServicNo()
				+ ", getOldMeterNo()=" + getOldMeterNo() + ", getNewMeterNo()=" + getNewMeterNo()
				+ ", getReadingMonth()=" + getReadingMonth() + ", getReadingYear()=" + getReadingYear()
				+ ", getTotalImportUnits()=" + getTotalImportUnits() + ", getTotalExportUnits()="
				+ getTotalExportUnits() + ", getRkvahUnits()=" + getRkvahUnits() + ", getTotalNetUnits()="
				+ getTotalNetUnits() + ", getRemarks()=" + getRemarks() + ", getImpC1Units()=" + getImpC1Units()
				+ ", getImpC2Units()=" + getImpC2Units() + ", getImpC3Units()=" + getImpC3Units() + ", getImpC4Units()="
				+ getImpC4Units() + ", getImpC5Units()=" + getImpC5Units() + ", getExpC1Units()=" + getExpC1Units()
				+ ", getExpC2Units()=" + getExpC2Units() + ", getExpC3Units()=" + getExpC3Units() + ", getExpC4Units()="
				+ getExpC4Units() + ", getExpC5Units()=" + getExpC5Units() + ", getNetC1Units()=" + getNetC1Units()
				+ ", getNetC2Units()=" + getNetC2Units() + ", getNetC3Units()=" + getNetC3Units() + ", getNetC4Units()="
				+ getNetC4Units() + ", getNetC5Units()=" + getNetC5Units() + ", getImportRemarks()="
				+ getImportRemarks() + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedBy()=" + getModifiedBy() + ", getModifiedDate()=" + getModifiedDate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
