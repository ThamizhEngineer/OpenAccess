package com.ss.oa.master.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="V_SERVICE_HISTORY")
@Entity
public class Generatorhis {
	
	@Id
    private Integer id;
	@Column(name="FLOW_TYPE_CODE")
	private String flowTypeCode;
	@Column(name="FUEL_TYPE_CODE")
	private String fuelTypeCode;
	@Column(name="M_COMPANY_ID")
	private String mCompanyId;
	@Column(name="M_COMP_SERV_NUMBER")
	private String mCompServNumber;
	@Column(name="M_COMPANY_NAME")
	private String mCompanyName;
	@Column(name="M_ORG_NAME")
	private String mOrgName;
	@Column(name="OLD_NAME")
	private String oldName;
	@Column(name="NEW_NAME")
	private String newName;
	@Column(name="NAME_CHANGE_MONTH")
	private String nameChangeMonth;
	@Column(name="NAME_CHANGE_YEAR")
	private String nameChangeYear;
	@Column(name="MIGRATED_FROM")
	private String migratedFrom;
	@Column(name="MIGRATED_TO")
	private String migratedTo;
	@Column(name="MIGRATION_MONTH")
	private String migrationMonth;
	@Column(name="MIGRATION_YEAR")
	private String migrationYear;
	@Column(name="ADDED_BUYER_NAME")
	private String addedBuyerName;
	@Column(name="ADDED_BUYER_SERVICE_NO")
	private String addedBuyerServiceNo;
	@Column(name="ADDED_BUYER_M_COMPANY_ID")
	private String addedBuyerMComapnyId;
	@Column(name="COMMERCIAL_MONTH")
	private String commercialMonth;
	@Column(name="COMMERCIAL_YEAR")
	private String commercialYear;
	@Column(name="REMOVED_BUYER_NAME")
	private String removedBuyerName;
	@Column(name="REMOVED_BUYER_SERVICE_NO")
	private String removedBuyerServiceno;
	@Column(name="REMOVED_BUYER_M_COMPANY_ID")
	private String removedBuyerMCompanyId; 
	@Column(name="OLD_METER_NO")
	private String Oldmeterno; 
	@Column(name="NEW_METER_NO")
	private String newmeterno; 
	@Column(name="MC_MONTH")
	private String mc_month; 
	
	
	
	public String getOldmeterno() {
		return Oldmeterno;
	}


	public void setOldmeterno(String oldmeterno) {
		Oldmeterno = oldmeterno;
	}


	public String getNewmeterno() {
		return newmeterno;
	}


	public void setNewmeterno(String newmeterno) {
		this.newmeterno = newmeterno;
	}


	public String getMc_month() {
		return mc_month;
	}


	public void setMc_month(String mc_month) {
		this.mc_month = mc_month;
	}


	public String getMcyear() {
		return Mcyear;
	}


	public void setMcyear(String mcyear) {
		Mcyear = mcyear;
	}


	@Column(name="MC_YEAR")
	private String Mcyear; 
	
	
	public Generatorhis() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Generatorhis(Integer id, String flowTypeCode, String fuelTypeCode, String mCompanyId, String mCompServNumber,
			String mCompanyName, String mOrgName, String oldName, String newName, String nameChangeMonth,
			String nameChangeYear, String migratedFrom, String migratedTo, String migrationMonth, String migrationYear,
			String addedBuyerName, String addedBuyerServiceNo, String addedBuyerMComapnyId, String commercialMonth,
			String commercialYear, String removedBuyerName, String removedBuyerServiceno,
			String removedBuyerMCompanyId) {
		super();
		this.id = id;
		this.flowTypeCode = flowTypeCode;
		this.fuelTypeCode = fuelTypeCode;
		this.mCompanyId = mCompanyId;
		this.mCompServNumber = mCompServNumber;
		this.mCompanyName = mCompanyName;
		this.mOrgName = mOrgName;
		this.oldName = oldName;
		this.newName = newName;
		this.nameChangeMonth = nameChangeMonth;
		this.nameChangeYear = nameChangeYear;
		this.migratedFrom = migratedFrom;
		this.migratedTo = migratedTo;
		this.migrationMonth = migrationMonth;
		this.migrationYear = migrationYear;
		this.addedBuyerName = addedBuyerName;
		this.addedBuyerServiceNo = addedBuyerServiceNo;
		this.addedBuyerMComapnyId = addedBuyerMComapnyId;
		this.commercialMonth = commercialMonth;
		this.commercialYear = commercialYear;
		this.removedBuyerName = removedBuyerName;
		this.removedBuyerServiceno = removedBuyerServiceno;
		this.removedBuyerMCompanyId = removedBuyerMCompanyId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFlowTypeCode() {
		return flowTypeCode;
	}


	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}


	public String getFuelTypeCode() {
		return fuelTypeCode;
	}


	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}


	public String getmCompanyId() {
		return mCompanyId;
	}


	public void setmCompanyId(String mCompanyId) {
		this.mCompanyId = mCompanyId;
	}


	public String getmCompServNumber() {
		return mCompServNumber;
	}


	public void setmCompServNumber(String mCompServNumber) {
		this.mCompServNumber = mCompServNumber;
	}


	public String getmCompanyName() {
		return mCompanyName;
	}


	public void setmCompanyName(String mCompanyName) {
		this.mCompanyName = mCompanyName;
	}


	public String getmOrgName() {
		return mOrgName;
	}


	public void setmOrgName(String mOrgName) {
		this.mOrgName = mOrgName;
	}


	public String getOldName() {
		return oldName;
	}


	public void setOldName(String oldName) {
		this.oldName = oldName;
	}


	public String getNewName() {
		return newName;
	}


	public void setNewName(String newName) {
		this.newName = newName;
	}


	public String getNameChangeMonth() {
		return nameChangeMonth;
	}


	public void setNameChangeMonth(String nameChangeMonth) {
		this.nameChangeMonth = nameChangeMonth;
	}


	public String getNameChangeYear() {
		return nameChangeYear;
	}


	public void setNameChangeYear(String nameChangeYear) {
		this.nameChangeYear = nameChangeYear;
	}


	public String getMigratedFrom() {
		return migratedFrom;
	}


	public void setMigratedFrom(String migratedFrom) {
		this.migratedFrom = migratedFrom;
	}


	public String getMigratedTo() {
		return migratedTo;
	}


	public void setMigratedTo(String migratedTo) {
		this.migratedTo = migratedTo;
	}


	public String getMigrationMonth() {
		return migrationMonth;
	}


	public void setMigrationMonth(String migrationMonth) {
		this.migrationMonth = migrationMonth;
	}


	public String getMigrationYear() {
		return migrationYear;
	}


	public void setMigrationYear(String migrationYear) {
		this.migrationYear = migrationYear;
	}


	public String getAddedBuyerName() {
		return addedBuyerName;
	}


	public void setAddedBuyerName(String addedBuyerName) {
		this.addedBuyerName = addedBuyerName;
	}


	public String getAddedBuyerServiceNo() {
		return addedBuyerServiceNo;
	}


	public void setAddedBuyerServiceNo(String addedBuyerServiceNo) {
		this.addedBuyerServiceNo = addedBuyerServiceNo;
	}


	public String getAddedBuyerMComapnyId() {
		return addedBuyerMComapnyId;
	}


	public void setAddedBuyerMComapnyId(String addedBuyerMComapnyId) {
		this.addedBuyerMComapnyId = addedBuyerMComapnyId;
	}


	public String getCommercialMonth() {
		return commercialMonth;
	}


	public void setCommercialMonth(String commercialMonth) {
		this.commercialMonth = commercialMonth;
	}


	public String getCommercialYear() {
		return commercialYear;
	}


	public void setCommercialYear(String commercialYear) {
		this.commercialYear = commercialYear;
	}


	public String getRemovedBuyerName() {
		return removedBuyerName;
	}


	public void setRemovedBuyerName(String removedBuyerName) {
		this.removedBuyerName = removedBuyerName;
	}


	public String getRemovedBuyerServiceno() {
		return removedBuyerServiceno;
	}


	public void setRemovedBuyerServiceno(String removedBuyerServiceno) {
		this.removedBuyerServiceno = removedBuyerServiceno;
	}


	public String getRemovedBuyerMCompanyId() {
		return removedBuyerMCompanyId;
	}


	public void setRemovedBuyerMCompanyId(String removedBuyerMCompanyId) {
		this.removedBuyerMCompanyId = removedBuyerMCompanyId;
	}


	@Override
	public String toString() {
		return "Generatorhis [id=" + id + ", flowTypeCode=" + flowTypeCode + ", fuelTypeCode=" + fuelTypeCode
				+ ", mCompanyId=" + mCompanyId + ", mCompServNumber=" + mCompServNumber + ", mCompanyName="
				+ mCompanyName + ", mOrgName=" + mOrgName + ", oldName=" + oldName + ", newName=" + newName
				+ ", nameChangeMonth=" + nameChangeMonth + ", nameChangeYear=" + nameChangeYear + ", migratedFrom="
				+ migratedFrom + ", migratedTo=" + migratedTo + ", migrationMonth=" + migrationMonth
				+ ", migrationYear=" + migrationYear + ", addedBuyerName=" + addedBuyerName + ", addedBuyerServiceNo="
				+ addedBuyerServiceNo + ", addedBuyerMComapnyId=" + addedBuyerMComapnyId + ", commercialMonth="
				+ commercialMonth + ", commercialYear=" + commercialYear + ", removedBuyerName=" + removedBuyerName
				+ ", removedBuyerServiceno=" + removedBuyerServiceno + ", removedBuyerMCompanyId="
				+ removedBuyerMCompanyId + "]";
	}

	
	
}
