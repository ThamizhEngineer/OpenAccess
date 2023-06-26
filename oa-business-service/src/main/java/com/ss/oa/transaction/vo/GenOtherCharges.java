package com.ss.oa.transaction.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="T_GEN_OTHER_CHARGES")
@CreationTimestamp	@UpdateTimestamp
@Component
public class GenOtherCharges {
	@Id
	private String id;
	@Formula("(SELECT COMP_SERV.M_COMPANY_ID FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerCompanyId;
	@Formula("(SELECT COMP_SERV.M_COMPANY_CODE FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerCompanyCode;
	@Formula("(SELECT COMP_SERV.M_COMPANY_NAME FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerCompanyName;
	@Column(name="M_COMPANY_SERVICE_ID")
	private String sellerCompanyServiceId;
	@Formula("(SELECT COMP_SERV.M_COMP_SERV_NUMBER FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerCompanyServiceNumber;
	@Formula("(SELECT COMP_SERV.M_ORG_ID FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerOrgId;
	@Formula("(SELECT COMP_SERV.M_ORG_CODE FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerOrgCode;
	@Formula("(SELECT COMP_SERV.M_ORG_NAME FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerOrgName;
	@Formula("(SELECT COMP_SERV.M_SUBSTATION_ID FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerSubstationId;
	@Formula("(SELECT COMP_SERV.M_SUBSTATION_CODE FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerSubstationCode;
	@Formula("(SELECT COMP_SERV.M_SUBSTATION_NAME FROM V_COMPANY_SERVICE COMP_SERV WHERE COMP_SERV.ID=M_COMPANY_SERVICE_ID)")
	private String sellerSubstationName;
	private String month;
	private String year;
	private String chargeCode;
	private String chargeDesc;
	private String chargeTypeCode;
	private String unitCharge;
	private String totalCharges;
	private String remarks;
	private String enabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="T_GEN_OTHER_CHARGE_ID")
	private List<GenOtherSubCharge> genOtherSubCharges;
	
	public GenOtherCharges() {
		super();
		// TODO Auto-generated constructor stub
	}

	// CONSTRUCTOR USING ALL FIELDS
	
	
	
	
	
	
	public GenOtherCharges(String id,String sellerCompanyName, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String sellerOrgId, String sellerOrgName, String month, String year) {
		super();
		this.id = id;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgName = sellerOrgName;
		this.month = month;
		this.year = year;
	}

	public GenOtherCharges(String id, String sellerCompanyId, String sellerCompanyCode, String sellerCompanyName,
			String sellerCompanyServiceId, String sellerCompanyServiceNumber, String sellerOrgId, String sellerOrgCode,
			String sellerOrgName, String sellerSubstationId, String sellerSubstationCode, String sellerSubstationName,
			String month, String year, String chargeCode, String chargeDesc, String chargeTypeCode, String unitCharge,
			String totalCharges, String remarks, String enabled, String createdBy, LocalDateTime createdDt,
			String modifiedBy, LocalDateTime modifiedDt, List<GenOtherSubCharge> genOtherSubCharges) {
		super();
		this.id = id;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyCode = sellerCompanyCode;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgCode = sellerOrgCode;
		this.sellerOrgName = sellerOrgName;
		this.sellerSubstationId = sellerSubstationId;
		this.sellerSubstationCode = sellerSubstationCode;
		this.sellerSubstationName = sellerSubstationName;
		this.month = month;
		this.year = year;
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeTypeCode = chargeTypeCode;
		this.unitCharge = unitCharge;
		this.totalCharges = totalCharges;
		this.remarks = remarks;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.genOtherSubCharges = genOtherSubCharges;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSellerCompanyId() {
		return sellerCompanyId;
	}

	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}

	public String getSellerCompanyCode() {
		return sellerCompanyCode;
	}

	public void setSellerCompanyCode(String sellerCompanyCode) {
		this.sellerCompanyCode = sellerCompanyCode;
	}

	public String getSellerCompanyName() {
		return sellerCompanyName;
	}

	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}

	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}

	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}

	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
	}

	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
	}

	public String getSellerOrgId() {
		return sellerOrgId;
	}

	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}

	public String getSellerOrgCode() {
		return sellerOrgCode;
	}

	public void setSellerOrgCode(String sellerOrgCode) {
		this.sellerOrgCode = sellerOrgCode;
	}

	public String getSellerOrgName() {
		return sellerOrgName;
	}

	public void setSellerOrgName(String sellerOrgName) {
		this.sellerOrgName = sellerOrgName;
	}

	public String getSellerSubstationId() {
		return sellerSubstationId;
	}

	public void setSellerSubstationId(String sellerSubstationId) {
		this.sellerSubstationId = sellerSubstationId;
	}

	public String getSellerSubstationCode() {
		return sellerSubstationCode;
	}

	public void setSellerSubstationCode(String sellerSubstationCode) {
		this.sellerSubstationCode = sellerSubstationCode;
	}

	public String getSellerSubstationName() {
		return sellerSubstationName;
	}

	public void setSellerSubstationName(String sellerSubstationName) {
		this.sellerSubstationName = sellerSubstationName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeDesc() {
		return chargeDesc;
	}

	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}

	public String getChargeTypeCode() {
		return chargeTypeCode;
	}

	public void setChargeTypeCode(String chargeTypeCode) {
		this.chargeTypeCode = chargeTypeCode;
	}

	public String getUnitCharge() {
		return unitCharge;
	}

	public void setUnitCharge(String unitCharge) {
		this.unitCharge = unitCharge;
	}

	public String getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(String totalCharges) {
		this.totalCharges = totalCharges;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
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

	public List<GenOtherSubCharge> getGenOtherSubCharges() {
		return genOtherSubCharges;
	}

	public void setGenOtherSubCharges(List<GenOtherSubCharge> genOtherSubCharges) {
		this.genOtherSubCharges = genOtherSubCharges;
	}

	@Override
	public String toString() {
		return "GenOtherCharges [id=" + id + ", sellerCompanyId=" + sellerCompanyId + ", sellerCompanyCode="
				+ sellerCompanyCode + ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyServiceId="
				+ sellerCompanyServiceId + ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber
				+ ", sellerOrgId=" + sellerOrgId + ", sellerOrgCode=" + sellerOrgCode + ", sellerOrgName="
				+ sellerOrgName + ", sellerSubstationId=" + sellerSubstationId + ", sellerSubstationCode="
				+ sellerSubstationCode + ", sellerSubstationName=" + sellerSubstationName + ", month=" + month
				+ ", year=" + year + ", chargeCode=" + chargeCode + ", chargeDesc=" + chargeDesc + ", chargeTypeCode="
				+ chargeTypeCode + ", unitCharge=" + unitCharge + ", totalCharges=" + totalCharges + ", remarks="
				+ remarks + ", enabled=" + enabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + ", genOtherSubCharges="
				+ genOtherSubCharges + "]";
	}

	
	

	
}
