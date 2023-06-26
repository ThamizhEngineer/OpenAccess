package com.ss.oashared.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Table(name="V_COMPANY_SERVICE")
@CreationTimestamp
@UpdateTimestamp
@Entity
@JsonInclude(Include.NON_NULL)
public class VCompanyService {
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	private String serviceId;
	@Column(name="COMP_SER_TYPE_CODE")
	private String serviceTypeCode;
	@Column(name="COMP_SER_TYPE_NAME")
	private String serviceTypeName;
	@Column(name="\"number\"" )
	private String serviceNumber;
	@Column(name="M_COMPANY_ID")
	private String companyId;
	@Column(name="M_COMPANY_CODE")
	private String companyCode;
	@Column(name="M_COMPANY_NAME")
	private String companyName;
	@Column(name="M_ORG_ID")
	private String orgId;
	@Column(name="M_ORG_CODE")
	private String orgCode;
	@Column(name="M_ORG_NAME")
	private String orgName;
	@Column(name="CAPACITY")
	private String capacity;
	@Column(name="IS_REC",columnDefinition="CHAR")
	private String isRec;
	@Column(name="IS_BUYER",columnDefinition="CHAR")
	private String isBuyer;
	@Column(name="IS_SELLER",columnDefinition="CHAR")
	private String isSeller;
	@Column(name="M_SUBSTATION_ID")
	private String substationId;
	@Column(name="M_SUBSTATION_CODE")
	private String substationCode;
	@Column(name="M_SUBSTATION_NAME")
	private String substationName;
	@Column(name="M_FEEDER_ID")
	private String feederId;
	@Column(name="m_comp_serv_number")
	private String mCompServNumber;
	@Column(name="M_FEEDER_CODE")
	private String feederCode;
	@Column(name="M_FEEDER_NAME")
	private String feederName;
	@Column(name="VOLTAGE_CODE")
	private String voltageCode;
	@Column(name="VOLTAGE_NAME")
	private String voltageName;
	@Column(name="TARIFF")
	private String tariff;
	@Column(name="M_COMPANY_METER_ID")
	private String companymeterId;
	@Column(name="MODEM_NUMBER")
	private String modemNumber;
	@Column(name="METER_NUMBER")
	private String meterNumber;
	@Column(name="MF")
	private String multiplicationFactor;
	@Column(name="METER_MAKE_CODE")
	private String meterMakeCode;
	@Column(name="FUEL_TYPE_CODE")
	private String fuelTypeCode;
	@Column(name="FUEL_TYPE_NAME")
	private String fuelTypeName;
	@Column(name="FUEL_GROUP_NAME")
	private String fuelGroupName;
	@Column(name="METER_CT1")
	private String meterCt1;
	@Column(name="METER_CT2")
	private String meterCt2;
	@Column(name="METER_CT3")
	private String meterCt3;
	@Column(name="METER_PT1")
	private String meterPt1;
	@Column(name="METER_PT2")
	private String meterPt2;
	@Column(name="METER_PT3")
	private String meterPt3;
	@Transient
	private Select selected;
	
	public enum Select{
		ORG,
		SERVICE,
		COMPANY,
		SUBSTATION,
		FEEDER,
		METER,
		COMPSERVICE
	}
	
	public VCompanyService() {
		// TODO Auto-generated constructor stub
	super();
	}

	 
	public VCompanyService(String orgId, String fuelGroupName) {
		super();
		this.orgId = orgId;
		this.fuelGroupName = fuelGroupName;
	}


	@Override
	public String toString() {
		return "VCompanyService [serviceId=" + serviceId + ", serviceTypeCode=" + serviceTypeCode + ", serviceTypeName="
				+ serviceTypeName + ", serviceNumber=" + serviceNumber + ", companyId=" + companyId + ", companyCode="
				+ companyCode + ", companyName=" + companyName + ", orgId=" + orgId + ", orgCode=" + orgCode
				+ ", orgName=" + orgName + ", capacity=" + capacity + ", isRec=" + isRec + ", isBuyer=" + isBuyer
				+ ", isSeller=" + isSeller + ", substationId=" + substationId + ", substationCode=" + substationCode
				+ ", substationName=" + substationName + ", feederId=" + feederId + ", mCompServNumber="
				+ mCompServNumber + ", feederCode=" + feederCode + ", feederName=" + feederName + ", voltageCode="
				+ voltageCode + ", voltageName=" + voltageName + ", tariff=" + tariff + ", companymeterId="
				+ companymeterId + ", modemNumber=" + modemNumber + ", meterNumber=" + meterNumber
				+ ", multiplicationFactor=" + multiplicationFactor + ", meterMakeCode=" + meterMakeCode
				+ ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName + ", fuelGroupName="
				+ fuelGroupName + ", meterCt1=" + meterCt1 + ", meterCt2=" + meterCt2 + ", meterCt3=" + meterCt3
				+ ", meterPt1=" + meterPt1 + ", meterPt2=" + meterPt2 + ", meterPt3=" + meterPt3 + ", selected="
				+ selected + "]";
	}


	public VCompanyService(String serviceId, String serviceTypeCode, String serviceTypeName, String serviceNumber,
			String companyId, String companyCode, String companyName, String orgId, String orgCode, String orgName,
			String capacity, String isRec, String isBuyer, String isSeller, String substationId, String substationCode,
			String substationName, String feederId, String mCompServNumber, String feederCode, String feederName,
			String voltageCode, String voltageName, String tariff, String companymeterId, String modemNumber,
			String meterNumber, String multiplicationFactor, String meterMakeCode, String fuelTypeCode,
			String fuelTypeName, String fuelGroupName, String meterCt1, String meterCt2, String meterCt3,
			String meterPt1, String meterPt2, String meterPt3, Select selected) {
		super();
		this.serviceId = serviceId;
		this.serviceTypeCode = serviceTypeCode;
		this.serviceTypeName = serviceTypeName;
		this.serviceNumber = serviceNumber;
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.capacity = capacity;
		this.isRec = isRec;
		this.isBuyer = isBuyer;
		this.isSeller = isSeller;
		this.substationId = substationId;
		this.substationCode = substationCode;
		this.substationName = substationName;
		this.feederId = feederId;
		this.mCompServNumber = mCompServNumber;
		this.feederCode = feederCode;
		this.feederName = feederName;
		this.voltageCode = voltageCode;
		this.voltageName = voltageName;
		this.tariff = tariff;
		this.companymeterId = companymeterId;
		this.modemNumber = modemNumber;
		this.meterNumber = meterNumber;
		this.multiplicationFactor = multiplicationFactor;
		this.meterMakeCode = meterMakeCode;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.fuelGroupName = fuelGroupName;
		this.meterCt1 = meterCt1;
		this.meterCt2 = meterCt2;
		this.meterCt3 = meterCt3;
		this.meterPt1 = meterPt1;
		this.meterPt2 = meterPt2;
		this.meterPt3 = meterPt3;
		this.selected = selected;
	}


	public String getServiceId() {
		return serviceId;
	}


	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	public String getServiceTypeCode() {
		return serviceTypeCode;
	}


	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}


	public String getServiceTypeName() {
		return serviceTypeName;
	}


	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}


	public String getServiceNumber() {
		return serviceNumber;
	}


	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
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


	public String getCapacity() {
		return capacity;
	}


	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	public String getIsRec() {
		return isRec;
	}


	public void setIsRec(String isRec) {
		this.isRec = isRec;
	}


	public String getIsBuyer() {
		return isBuyer;
	}


	public void setIsBuyer(String isBuyer) {
		this.isBuyer = isBuyer;
	}


	public String getIsSeller() {
		return isSeller;
	}


	public void setIsSeller(String isSeller) {
		this.isSeller = isSeller;
	}


	public String getSubstationId() {
		return substationId;
	}


	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}


	public String getSubstationCode() {
		return substationCode;
	}


	public void setSubstationCode(String substationCode) {
		this.substationCode = substationCode;
	}


	public String getSubstationName() {
		return substationName;
	}


	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}


	public String getFeederId() {
		return feederId;
	}


	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}


	public String getmCompServNumber() {
		return mCompServNumber;
	}


	public void setmCompServNumber(String mCompServNumber) {
		this.mCompServNumber = mCompServNumber;
	}


	public String getFeederCode() {
		return feederCode;
	}


	public void setFeederCode(String feederCode) {
		this.feederCode = feederCode;
	}


	public String getFeederName() {
		return feederName;
	}


	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}


	public String getVoltageCode() {
		return voltageCode;
	}


	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}


	public String getVoltageName() {
		return voltageName;
	}


	public void setVoltageName(String voltageName) {
		this.voltageName = voltageName;
	}


	public String getTariff() {
		return tariff;
	}


	public void setTariff(String tariff) {
		this.tariff = tariff;
	}


	public String getCompanymeterId() {
		return companymeterId;
	}


	public void setCompanymeterId(String companymeterId) {
		this.companymeterId = companymeterId;
	}


	public String getModemNumber() {
		return modemNumber;
	}


	public void setModemNumber(String modemNumber) {
		this.modemNumber = modemNumber;
	}


	public String getMeterNumber() {
		return meterNumber;
	}


	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}


	public String getMultiplicationFactor() {
		return multiplicationFactor;
	}


	public void setMultiplicationFactor(String multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
	}


	public String getMeterMakeCode() {
		return meterMakeCode;
	}


	public void setMeterMakeCode(String meterMakeCode) {
		this.meterMakeCode = meterMakeCode;
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


	public String getFuelGroupName() {
		return fuelGroupName;
	}


	public void setFuelGroupName(String fuelGroupName) {
		this.fuelGroupName = fuelGroupName;
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


	public Select getSelected() {
		return selected;
	}


	public void setSelected(Select selected) {
		this.selected = selected;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
