package com.ss.oa.transaction.MeterChange;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INT_METER_CHANGE")
public class IntMeterChange {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String id;
	
	private String serviceNo;
	
	private String oldMeterNo;
	
	private String newMeterNo;
	
	private String readingMonth;
	
	private String readingYear;
	
	private String totalImportUnits;
	
	private String totalExportUnits;
	
	private String rkvahUnits;
	
    private String totalNetUnits;
    
    private String remarks;
    
    @Column(name="IMP_C1_UNITS")
    private String impC1Uinits;
    
    @Column(name="IMP_C2_UNITS")
    private String impC2Units;
    
    @Column(name="IMP_C3_UNITS")
    private String impC3Units;
    
    @Column(name="IMP_C4_UNITS")
    private String impC4Units;
    
    @Column(name="IMP_C5_UNITS")
    private String impC5Units;
    
    @Column(name="EXP_C1_UNITS")
    private String expC1Units;
    
    @Column(name="EXP_C2_UNITS")
    private String expC2Units;
    
    @Column(name="EXP_C3_UNITS")
    private String expC3Units;
    
    @Column(name="EXP_C4_UNITS")
    private String expC4Units;
    
    @Column(name="EXP_C5_UNITS")
    private String expC5Units;
    
    @Column(name="NET_C1_UNITS")
    private String netC1Units;
    
    @Column(name="NET_C2_UNITS")
    private String netC2Units;
    
    @Column(name="NET_C3_UNITS")
    private String netC3Units;
    
    @Column(name="NET_C4_UNITS")
    private String netC4Units;
  
    @Column(name="NET_C5_UNITS")
    private String netC5Units;
    
    private String importRemarks;
    
    private String createdBy;
    
    private String createdDate;
    
    private String modifiedBy;
    
    private String modifiedDate;
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getOldMeterNo() {
		return oldMeterNo;
	}

	public void setOldMeterNo(String oldMeterNo) {
		this.oldMeterNo = oldMeterNo;
	}

	public String getNewMeterNo() {
		return newMeterNo;
	}

	public void setNewMeterNo(String newMeterNo) {
		this.newMeterNo = newMeterNo;
	}

	public String getReadingMonth() {
		return readingMonth;
	}

	public void setReadingMonth(String readingMonth) {
		this.readingMonth = readingMonth;
	}

	public String getReadingYear() {
		return readingYear;
	}

	public void setReadingYear(String readingYear) {
		this.readingYear = readingYear;
	}

	public String getTotalImportUnits() {
		return totalImportUnits;
	}

	public void setTotalImportUnits(String totalImportUnits) {
		this.totalImportUnits = totalImportUnits;
	}

	public String getTotalExportUnits() {
		return totalExportUnits;
	}

	public void setTotalExportUnits(String totalExportUnits) {
		this.totalExportUnits = totalExportUnits;
	}

	public String getRkvahUnits() {
		return rkvahUnits;
	}

	public void setRkvahUnits(String rkvahUnits) {
		this.rkvahUnits = rkvahUnits;
	}

	public String getTotalNetUnits() {
		return totalNetUnits;
	}

	public void setTotalNetUnits(String totalNetUnits) {
		this.totalNetUnits = totalNetUnits;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getImpC1Uinits() {
		return impC1Uinits;
	}

	public void setImpC1Uinits(String impC1Uinits) {
		this.impC1Uinits = impC1Uinits;
	}

	public String getImptC2Units() {
		return impC2Units;
	}

	public void setImptC2Units(String imptC2Units) {
		this.impC2Units = imptC2Units;
	}

	public String getImpC3Units() {
		return impC3Units;
	}

	public void setImpC3Units(String impC3Units) {
		this.impC3Units = impC3Units;
	}

	public String getImpC4Units() {
		return impC4Units;
	}

	public void setImpC4Units(String impC4Units) {
		this.impC4Units = impC4Units;
	}

	public String getImpC5Units() {
		return impC5Units;
	}

	public void setImpC5Units(String impC5Units) {
		this.impC5Units = impC5Units;
	}

	public String getExpC1Units() {
		return expC1Units;
	}

	public void setExpC1Units(String expC1Units) {
		this.expC1Units = expC1Units;
	}

	public String getExpC2Units() {
		return expC2Units;
	}

	public void setExpC2Units(String expC2Units) {
		this.expC2Units = expC2Units;
	}

	public String getExpC3Units() {
		return expC3Units;
	}

	public void setExpC3Units(String expC3Units) {
		this.expC3Units = expC3Units;
	}

	public String getExpC4Units() {
		return expC4Units;
	}

	public void setExpC4Units(String expC4Units) {
		this.expC4Units = expC4Units;
	}

	public String getExpC5Units() {
		return expC5Units;
	}

	public void setExpC5Units(String expC5Units) {
		this.expC5Units = expC5Units;
	}

	public String getNetC1Units() {
		return netC1Units;
	}

	public void setNetC1Units(String netC1Units) {
		this.netC1Units = netC1Units;
	}

	public String getNetC2Units() {
		return netC2Units;
	}

	public void setNetC2Units(String netC2Units) {
		this.netC2Units = netC2Units;
	}

	public String getNetC3Units() {
		return netC3Units;
	}

	public void setNetC3Units(String netC3Units) {
		this.netC3Units = netC3Units;
	}

	public String getNetC4Units() {
		return netC4Units;
	}

	public void setNetC4Units(String netC4Units) {
		this.netC4Units = netC4Units;
	}

	public String getNetC5Units() {
		return netC5Units;
	}

	public void setNetC5Units(String netC5Units) {
		this.netC5Units = netC5Units;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
    
}
