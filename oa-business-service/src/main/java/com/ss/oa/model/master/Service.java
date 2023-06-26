package com.ss.oa.model.master;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="M_COMPANY_SERVICE")
@CreationTimestamp
@UpdateTimestamp
@Entity
@Scope("prototype")
public class Service implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@Column(name="COMP_SER_TYPE_CODE")
	private String typeCode;
	@Column(name="\"number\"", nullable = false)
	private String number;
	@Column(name="M_COMPANY_ID")
	private String companyId;
	@Column(name="M_ORG_ID")
	private String orgId;
	@Column(name="CAPACITY")
	private String capacity;
	@Column(name="M_SUBSTATION_ID")
	private String substationId;
	
	@Column(name="M_SECTION_ID")
	private String sectionId;
	
	@Column(name="M_FEEDER_ID")
	private String feederId;
	private String refNumber;
	private String voltageCode;
	private String tariff;
	@Column(name="TOTAL_CAPACITY")
	private String Totalcapacity;
	@Column(columnDefinition = "char") 
	private String enabled;
	private String remarks;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate ModifiedDate;
	private String type;
	private String bankingServiceId;
	private String bankingServiceNumber;
	private String tlServiceId;
	private String tlServiceNumber;
	private String dlServiceId;
	private String dlServiceNumber;
	private String unadjustedServiceId;
	private String unadjustedServiceNumber;
	private String importRemarks;
	
	@Formula("(SELECT typecodes.VALUE_DESC FROM M_COMPANY_SERVICE service JOIN v_codes typecodes on service.COMP_SER_TYPE_CODE = typecodes.value_code and typecodes.list_code='SERVICE_TYPE_CODE' WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String typeName;
	@Formula("(SELECT voltagecodes.VALUE_DESC FROM M_COMPANY_SERVICE service JOIN v_codes voltagecodes on service.VOLTAGE_CODE= voltagecodes.Value_Code AND  voltagecodes.list_code = 'VOLTAGE_CODE' WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String voltageName;
	@Formula("(SELECT feeder.NAME FROM M_COMPANY_SERVICE service JOIN M_FEEDER feeder on feeder.id = service.M_FEEDER_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String feederName;
	@Formula("(SELECT feeder.CODE FROM M_COMPANY_SERVICE service JOIN M_FEEDER feeder on feeder.id = service.M_FEEDER_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String feederCode;
	@Formula("(SELECT org.CODE FROM M_COMPANY_SERVICE service JOIN M_ORG org on org.id=service.M_ORG_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String orgCode;
	@Formula("(SELECT org.NAME FROM M_COMPANY_SERVICE service JOIN M_ORG org on org.id=service.M_ORG_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String orgName;
	@Formula("(SELECT company.CODE FROM M_COMPANY_SERVICE service JOIN M_COMPANY company on company.id =service.M_COMPANY_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String companyCode;
	@Formula("(SELECT company.NAME FROM M_COMPANY_SERVICE service JOIN M_COMPANY company on company.id =service.M_COMPANY_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String companyName;
	
	@Transient
	private String meterNumber;
	@Transient
	private String isAbtMeter;
	@Transient
	private String meterMakeCode;
	@Transient
	private String code;
	@Transient
	private String multiplicationFactor;
//	@Transient
	@Column(name="FUEL_TYPE_CODE")
	private String fuelTypeCode;
	@Transient
	private String fuelTypeName;
	@Transient
	private String modemNumber;
	@Transient
	private String meterCt1;
	@Transient
	private String meterCt2;
	@Transient
	private String meterCt3;
	@Transient
	private String meterPt1;
	@Transient
	private String meterPt2;
	@Transient
	private String meterPt3;
	@Column(name="IS_REC",columnDefinition = "CHAR(1)") 
	private String isRec;
	private String flowTypeCode;
	private String typeOfShare;
	private String installedBy;
	private String solarCharges;

    private String contactFullName,contactDesignation,contactEmail,contactPhoneNo,regOfficeAddr,plantAddr,isContactVerified;
    @Transient
    private String companyTypeName,companyTypeCode,pan, cin, llpin, gst ,tan;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime contactLastVerifiedDate; 
	
	@Column(name="BANK_IFSC_CODE")
	private String bankIfscCode;
	
	@Column(name="BANK_NAME")
	private String bankName;
	
	@Column(name="BANK_ACCOUNT_NO")
	private String bankAccountNo;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="M_COMPANY_SERVICE_ID")
	private List<Meter> meters;

	public Service() {
		super();

	}

	public Service(String id, String typeCode, String number, String companyId, String orgId, String capacity,
			String substationId, String sectionId, String feederId, String refNumber, String voltageCode, String tariff,
			String totalcapacity, String enabled, String remarks, String createdBy, LocalDate createdDate,
			String modifiedBy, LocalDate modifiedDate, String type, String bankingServiceId,
			String bankingServiceNumber, String tlServiceId, String tlServiceNumber, String dlServiceId,
			String dlServiceNumber, String unadjustedServiceId, String unadjustedServiceNumber, String importRemarks,
			String typeName, String voltageName, String feederName, String feederCode, String orgCode, String orgName,
			String companyCode, String companyName, String meterNumber, String isAbtMeter, String meterMakeCode,
			String code, String multiplicationFactor, String fuelTypeCode, String fuelTypeName, String modemNumber,
			String meterCt1, String meterCt2, String meterCt3, String meterPt1, String meterPt2, String meterPt3,
			String isRec, String flowTypeCode, String typeOfShare, String installedBy, String solarCharges,
			String contactFullName, String contactDesignation, String contactEmail, String contactPhoneNo,
			String regOfficeAddr, String plantAddr, String isContactVerified, String companyTypeName,
			String companyTypeCode, String pan, String cin, String llpin, String gst, String tan,
			LocalDateTime contactLastVerifiedDate, String bankIfscCode, String bankName, String bankAccountNo,
			List<Meter> meters) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.number = number;
		this.companyId = companyId;
		this.orgId = orgId;
		this.capacity = capacity;
		this.substationId = substationId;
		this.sectionId = sectionId;
		this.feederId = feederId;
		this.refNumber = refNumber;
		this.voltageCode = voltageCode;
		this.tariff = tariff;
		Totalcapacity = totalcapacity;
		this.enabled = enabled;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		ModifiedDate = modifiedDate;
		this.type = type;
		this.bankingServiceId = bankingServiceId;
		this.bankingServiceNumber = bankingServiceNumber;
		this.tlServiceId = tlServiceId;
		this.tlServiceNumber = tlServiceNumber;
		this.dlServiceId = dlServiceId;
		this.dlServiceNumber = dlServiceNumber;
		this.unadjustedServiceId = unadjustedServiceId;
		this.unadjustedServiceNumber = unadjustedServiceNumber;
		this.importRemarks = importRemarks;
		this.typeName = typeName;
		this.voltageName = voltageName;
		this.feederName = feederName;
		this.feederCode = feederCode;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.meterNumber = meterNumber;
		this.isAbtMeter = isAbtMeter;
		this.meterMakeCode = meterMakeCode;
		this.code = code;
		this.multiplicationFactor = multiplicationFactor;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.modemNumber = modemNumber;
		this.meterCt1 = meterCt1;
		this.meterCt2 = meterCt2;
		this.meterCt3 = meterCt3;
		this.meterPt1 = meterPt1;
		this.meterPt2 = meterPt2;
		this.meterPt3 = meterPt3;
		this.isRec = isRec;
		this.flowTypeCode = flowTypeCode;
		this.typeOfShare = typeOfShare;
		this.installedBy = installedBy;
		this.solarCharges = solarCharges;
		this.contactFullName = contactFullName;
		this.contactDesignation = contactDesignation;
		this.contactEmail = contactEmail;
		this.contactPhoneNo = contactPhoneNo;
		this.regOfficeAddr = regOfficeAddr;
		this.plantAddr = plantAddr;
		this.isContactVerified = isContactVerified;
		this.companyTypeName = companyTypeName;
		this.companyTypeCode = companyTypeCode;
		this.pan = pan;
		this.cin = cin;
		this.llpin = llpin;
		this.gst = gst;
		this.tan = tan;
		this.contactLastVerifiedDate = contactLastVerifiedDate;
		this.bankIfscCode = bankIfscCode;
		this.bankName = bankName;
		this.bankAccountNo = bankAccountNo;
		this.meters = meters;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getSubstationId() {
		return substationId;
	}

	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getFeederId() {
		return feederId;
	}

	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getVoltageCode() {
		return voltageCode;
	}

	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}

	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public String getTotalcapacity() {
		return Totalcapacity;
	}

	public void setTotalcapacity(String totalcapacity) {
		Totalcapacity = totalcapacity;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBankingServiceId() {
		return bankingServiceId;
	}

	public void setBankingServiceId(String bankingServiceId) {
		this.bankingServiceId = bankingServiceId;
	}

	public String getBankingServiceNumber() {
		return bankingServiceNumber;
	}

	public void setBankingServiceNumber(String bankingServiceNumber) {
		this.bankingServiceNumber = bankingServiceNumber;
	}

	public String getTlServiceId() {
		return tlServiceId;
	}

	public void setTlServiceId(String tlServiceId) {
		this.tlServiceId = tlServiceId;
	}

	public String getTlServiceNumber() {
		return tlServiceNumber;
	}

	public void setTlServiceNumber(String tlServiceNumber) {
		this.tlServiceNumber = tlServiceNumber;
	}

	public String getDlServiceId() {
		return dlServiceId;
	}

	public void setDlServiceId(String dlServiceId) {
		this.dlServiceId = dlServiceId;
	}

	public String getDlServiceNumber() {
		return dlServiceNumber;
	}

	public void setDlServiceNumber(String dlServiceNumber) {
		this.dlServiceNumber = dlServiceNumber;
	}

	public String getUnadjustedServiceId() {
		return unadjustedServiceId;
	}

	public void setUnadjustedServiceId(String unadjustedServiceId) {
		this.unadjustedServiceId = unadjustedServiceId;
	}

	public String getUnadjustedServiceNumber() {
		return unadjustedServiceNumber;
	}

	public void setUnadjustedServiceNumber(String unadjustedServiceNumber) {
		this.unadjustedServiceNumber = unadjustedServiceNumber;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getVoltageName() {
		return voltageName;
	}

	public void setVoltageName(String voltageName) {
		this.voltageName = voltageName;
	}

	public String getFeederName() {
		return feederName;
	}

	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}

	public String getFeederCode() {
		return feederCode;
	}

	public void setFeederCode(String feederCode) {
		this.feederCode = feederCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getIsAbtMeter() {
		return isAbtMeter;
	}

	public void setIsAbtMeter(String isAbtMeter) {
		this.isAbtMeter = isAbtMeter;
	}

	public String getMeterMakeCode() {
		return meterMakeCode;
	}

	public void setMeterMakeCode(String meterMakeCode) {
		this.meterMakeCode = meterMakeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public void setMultiplicationFactor(String multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public String getModemNumber() {
		return modemNumber;
	}

	public void setModemNumber(String modemNumber) {
		this.modemNumber = modemNumber;
	}

	public String getMeterCt1() {
		return meterCt1;
	}

	public void setMeterCt1(String meterCt1) {
		this.meterCt1 = meterCt1;
	}

	public String getMeterCt2() {
		return meterCt2;
	}

	public void setMeterCt2(String meterCt2) {
		this.meterCt2 = meterCt2;
	}

	public String getMeterCt3() {
		return meterCt3;
	}

	public void setMeterCt3(String meterCt3) {
		this.meterCt3 = meterCt3;
	}

	public String getMeterPt1() {
		return meterPt1;
	}

	public void setMeterPt1(String meterPt1) {
		this.meterPt1 = meterPt1;
	}

	public String getMeterPt2() {
		return meterPt2;
	}

	public void setMeterPt2(String meterPt2) {
		this.meterPt2 = meterPt2;
	}

	public String getMeterPt3() {
		return meterPt3;
	}

	public void setMeterPt3(String meterPt3) {
		this.meterPt3 = meterPt3;
	}

	public String getIsRec() {
		return isRec;
	}

	public void setIsRec(String isRec) {
		this.isRec = isRec;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public String getTypeOfShare() {
		return typeOfShare;
	}

	public void setTypeOfShare(String typeOfShare) {
		this.typeOfShare = typeOfShare;
	}

	public String getInstalledBy() {
		return installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	public String getSolarCharges() {
		return solarCharges;
	}

	public void setSolarCharges(String solarCharges) {
		this.solarCharges = solarCharges;
	}

	public String getContactFullName() {
		return contactFullName;
	}

	public void setContactFullName(String contactFullName) {
		this.contactFullName = contactFullName;
	}

	public String getContactDesignation() {
		return contactDesignation;
	}

	public void setContactDesignation(String contactDesignation) {
		this.contactDesignation = contactDesignation;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhoneNo() {
		return contactPhoneNo;
	}

	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}

	public String getRegOfficeAddr() {
		return regOfficeAddr;
	}

	public void setRegOfficeAddr(String regOfficeAddr) {
		this.regOfficeAddr = regOfficeAddr;
	}

	public String getPlantAddr() {
		return plantAddr;
	}

	public void setPlantAddr(String plantAddr) {
		this.plantAddr = plantAddr;
	}

	public String getIsContactVerified() {
		return isContactVerified;
	}

	public void setIsContactVerified(String isContactVerified) {
		this.isContactVerified = isContactVerified;
	}

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	public String getCompanyTypeCode() {
		return companyTypeCode;
	}

	public void setCompanyTypeCode(String companyTypeCode) {
		this.companyTypeCode = companyTypeCode;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getLlpin() {
		return llpin;
	}

	public void setLlpin(String llpin) {
		this.llpin = llpin;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getTan() {
		return tan;
	}

	public void setTan(String tan) {
		this.tan = tan;
	}

	public LocalDateTime getContactLastVerifiedDate() {
		return contactLastVerifiedDate;
	}

	public void setContactLastVerifiedDate(LocalDateTime contactLastVerifiedDate) {
		this.contactLastVerifiedDate = contactLastVerifiedDate;
	}

	public String getBankIfscCode() {
		return bankIfscCode;
	}

	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public List<Meter> getMeters() {
		return meters;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", typeCode=" + typeCode + ", number=" + number + ", companyId=" + companyId
				+ ", orgId=" + orgId + ", capacity=" + capacity + ", substationId=" + substationId + ", sectionId="
				+ sectionId + ", feederId=" + feederId + ", refNumber=" + refNumber + ", voltageCode=" + voltageCode
				+ ", tariff=" + tariff + ", Totalcapacity=" + Totalcapacity + ", enabled=" + enabled + ", remarks="
				+ remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", ModifiedDate=" + ModifiedDate + ", type=" + type + ", bankingServiceId=" + bankingServiceId
				+ ", bankingServiceNumber=" + bankingServiceNumber + ", tlServiceId=" + tlServiceId
				+ ", tlServiceNumber=" + tlServiceNumber + ", dlServiceId=" + dlServiceId + ", dlServiceNumber="
				+ dlServiceNumber + ", unadjustedServiceId=" + unadjustedServiceId + ", unadjustedServiceNumber="
				+ unadjustedServiceNumber + ", importRemarks=" + importRemarks + ", typeName=" + typeName
				+ ", voltageName=" + voltageName + ", feederName=" + feederName + ", feederCode=" + feederCode
				+ ", orgCode=" + orgCode + ", orgName=" + orgName + ", companyCode=" + companyCode + ", companyName="
				+ companyName + ", meterNumber=" + meterNumber + ", isAbtMeter=" + isAbtMeter + ", meterMakeCode="
				+ meterMakeCode + ", code=" + code + ", multiplicationFactor=" + multiplicationFactor
				+ ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName + ", modemNumber=" + modemNumber
				+ ", meterCt1=" + meterCt1 + ", meterCt2=" + meterCt2 + ", meterCt3=" + meterCt3 + ", meterPt1="
				+ meterPt1 + ", meterPt2=" + meterPt2 + ", meterPt3=" + meterPt3 + ", isRec=" + isRec
				+ ", flowTypeCode=" + flowTypeCode + ", typeOfShare=" + typeOfShare + ", installedBy=" + installedBy
				+ ", solarCharges=" + solarCharges + ", contactFullName=" + contactFullName + ", contactDesignation="
				+ contactDesignation + ", contactEmail=" + contactEmail + ", contactPhoneNo=" + contactPhoneNo
				+ ", regOfficeAddr=" + regOfficeAddr + ", plantAddr=" + plantAddr + ", isContactVerified="
				+ isContactVerified + ", companyTypeName=" + companyTypeName + ", companyTypeCode=" + companyTypeCode
				+ ", pan=" + pan + ", cin=" + cin + ", llpin=" + llpin + ", gst=" + gst + ", tan=" + tan
				+ ", contactLastVerifiedDate=" + contactLastVerifiedDate + ", bankIfscCode=" + bankIfscCode
				+ ", bankName=" + bankName + ", bankAccountNo=" + bankAccountNo + ", meters=" + meters + "]";
	}

	public void setMeters(List<Meter> meters) {
		this.meters = meters;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
