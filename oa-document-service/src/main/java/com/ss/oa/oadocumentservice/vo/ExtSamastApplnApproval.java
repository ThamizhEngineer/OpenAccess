package com.ss.oa.oadocumentservice.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "EXT_SAMAST_APPLN_APPROVAL")
@CreationTimestamp
@UpdateTimestamp
@Entity
public class ExtSamastApplnApproval {
	
	@SequenceGenerator(name = "id_EXT_SAMAST_APPLN_APPROVAL_SEQ", sequenceName = "EXT_SAMAST_APPLN_APPROVAL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_EXT_SAMAST_APPLN_APPROVAL_SEQ")
	@Id
	private Integer id;
	@Column(name="APP_ID")
	private Integer appId;	
	@Column(name="APPL_REF_NO")
	private String applRefNo;
	@Column(name="APPL_NO")
	private String applNo;
	@Column(name="APPL_DATE")
	private LocalDate applDate;
	@Column(name="APP_CATEGORY")
	private String appCategory;
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	@Column(name="ENTITY_INJ")
	private String entityInj;
	@Column(name="ENTITY_INJ_EDC")
	private String entityInjEdc;
	@Column(name="ENTITY_DRL")
	private String entityDrl;
	@Column(name="ENTITY_DRL_EDC")
	private String entityDrlEdc;
	@Column(name="PERIOD_START_DATE")
	private LocalDate periodStartDate;
	@Column(name="PERIOD_END_DATE")
	private LocalDate periodEndDate;
	@Column(name="QUANTUM")
	private Float quantum;
	@Column(name="CATEGORY1")
	private String category1;
	@Column(name="CATEGORY2")
	private String category2;
	@Column(name="EOR_REG_TYPE")
	private String eorRegType;
	@Column(name="EOS_GC_APPROVAL_NUMBER")
	private String eosGcApprovalNumber;
	@Column(name="EOS_FEEDER_NAME_SELLER")
	private String eosFeederNameSeller;
	@Column(name="EOS_VOL_LVL_FEEDER")
	private String eosVolLvlFeeder;
	@Column(name="EOS_INJ_SUBSTATION")
	private String eosInjSubstation;
	@Column(name="EOS_VOL_LVL_SUBSTATION")
	private String eosVolLvlSubstation;
	@Column(name="EOC_LOSS_PER")
	private Float eocLossPer;
	@Column(name="EVACUATION_CAPACITY")
	private Float evacuationCapacity;
	@Column(name="EOS_UTIL_INJ_EMBED")
	private String eosUtilInjEmbed;
	@Column(name="EOS_UTIL_INJ_EMBED_LABEL")
	private String eosUtilInjEmbedLabel;
	@Column(name="EOB_UTIL_DRAWAL_EMBED_LABEL")
	private String eobUtilDrawalEmbedLabel;
	@Column(name="EOB_UTIL_DRA_EMBED")
	private String eobUtilDraEmbed;
	@Column(name="EOB_HT_CONSUMER_NUMBER")
	private String eobHtConsumerNumber;
	@Column(name="EOB_FEEDER_NAME_BUYER")
	private String eobFeederNameBuyer;
	@Column(name="EOB_VOL_LVL_FEEDER")
	private String eobVolLvlFeeder;
	@Column(name="EOB_DRA_SUBSTATION")
	private String eobDraSubstation;
	@Column(name="EOB_VOL_LVL_SUBSTATION")
	private String eobVolLvlSubstation;
	@Column(name="EOB_DRAWAL_LIMIT")
	private Float eobDrawalLimit;
	@Column(name="EOB_BUYER_TYPE")
	private String eobBuyerType;
	@Column(name="EOI_REG_NAME")
	private String eoiRegName;
	@Column(name="EOI_REG_ADDRESS")
	private String eoiRegAddress;
	@Column(name="EOI_REG_MOBILE1_NO")
	private String eoiRegMobile1No;
	@Column(name="EDC_STATUS")
	private String edcStatus;
	@Column(name="APPLICATION_STATUS")
	private String applicationStatus;
	@Column(name="APP_TYPE")
	private String appType;
	@Column(name="BATCH_KEY")
	private String batchKey;
	@Column(name="IS_CLEAN")
	private String isClean;
	@Column(name="INPUT_REMARKS")
	private String inputRemarks;
	@Column(name="IP_APPR_NO")
	private String ipApprNo;
	@Column(name="SELLER_EDC_CODE")
	private String sellerEdcCode;
	@Column(name="BUYER_EDC_CODE")
	private String buyerEdcCode;
	@Column(name="APPROVAL_NO")
	private String approvalNo;
	
	private String enabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpApprNo() {
		return ipApprNo;
	}

	public void setIpApprNo(String ipApprNo) {
		this.ipApprNo = ipApprNo;
	}

	public String getSellerEdcCode() {
		return sellerEdcCode;
	}

	public void setSellerEdcCode(String sellerEdcCode) {
		this.sellerEdcCode = sellerEdcCode;
	}

	public String getBuyerEdcCode() {
		return buyerEdcCode;
	}

	public void setBuyerEdcCode(String buyerEdcCode) {
		this.buyerEdcCode = buyerEdcCode;
	}

	public String getApprovalNo() {
		return approvalNo;
	}

	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}

	public ExtSamastApplnApproval() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getApplRefNo() {
		return applRefNo;
	}
	public void setApplRefNo(String applRefNo) {
		this.applRefNo = applRefNo;
	}
	public String getApplNo() {
		return applNo;
	}
	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}
	public LocalDate getApplDate() {
		return applDate;
	}
	public void setApplDate(LocalDate applDate) {
		this.applDate = applDate;
	}
	public String getAppCategory() {
		return appCategory;
	}
	public void setAppCategory(String appCategory) {
		this.appCategory = appCategory;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEntityInj() {
		return entityInj;
	}
	public void setEntityInj(String entityInj) {
		this.entityInj = entityInj;
	}
	public String getEntityInjEdc() {
		return entityInjEdc;
	}
	public void setEntityInjEdc(String entityInjEdc) {
		this.entityInjEdc = entityInjEdc;
	}
	public String getEntityDrl() {
		return entityDrl;
	}
	public void setEntityDrl(String entityDrl) {
		this.entityDrl = entityDrl;
	}
	public String getEntityDrlEdc() {
		return entityDrlEdc;
	}
	public void setEntityDrlEdc(String entityDrlEdc) {
		this.entityDrlEdc = entityDrlEdc;
	}
	public LocalDate getPeriodStartDate() {
		return periodStartDate;
	}
	public void setPeriodStartDate(LocalDate periodStartDate) {
		this.periodStartDate = periodStartDate;
	}
	public LocalDate getPeriodEndDate() {
		return periodEndDate;
	}
	public void setPeriodEndDate(LocalDate periodEndDate) {
		this.periodEndDate = periodEndDate;
	}
	public Float getQuantum() {
		return quantum;
	}
	public void setQuantum(Float quantum) {
		this.quantum = quantum;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	public String getEorRegType() {
		return eorRegType;
	}
	public void setEorRegType(String eorRegType) {
		this.eorRegType = eorRegType;
	}
	public String getEosGcApprovalNumber() {
		return eosGcApprovalNumber;
	}
	public void setEosGcApprovalNumber(String eosGcApprovalNumber) {
		this.eosGcApprovalNumber = eosGcApprovalNumber;
	}
	public String getEosFeederNameSeller() {
		return eosFeederNameSeller;
	}
	public void setEosFeederNameSeller(String eosFeederNameSeller) {
		this.eosFeederNameSeller = eosFeederNameSeller;
	}
	public String getEosVolLvlFeeder() {
		return eosVolLvlFeeder;
	}
	public void setEosVolLvlFeeder(String eosVolLvlFeeder) {
		this.eosVolLvlFeeder = eosVolLvlFeeder;
	}
	public String getEosInjSubstation() {
		return eosInjSubstation;
	}
	public void setEosInjSubstation(String eosInjSubstation) {
		this.eosInjSubstation = eosInjSubstation;
	}
	public String getEosVolLvlSubstation() {
		return eosVolLvlSubstation;
	}
	public void setEosVolLvlSubstation(String eosVolLvlSubstation) {
		this.eosVolLvlSubstation = eosVolLvlSubstation;
	}
	public Float getEocLossPer() {
		return eocLossPer;
	}
	public void setEocLossPer(Float eocLossPer) {
		this.eocLossPer = eocLossPer;
	}
	public Float getEvacuationCapacity() {
		return evacuationCapacity;
	}
	public void setEvacuationCapacity(Float evacuationCapacity) {
		this.evacuationCapacity = evacuationCapacity;
	}
	public String getEosUtilInjEmbed() {
		return eosUtilInjEmbed;
	}
	public void setEosUtilInjEmbed(String eosUtilInjEmbed) {
		this.eosUtilInjEmbed = eosUtilInjEmbed;
	}
	public String getEosUtilInjEmbedLabel() {
		return eosUtilInjEmbedLabel;
	}
	public void setEosUtilInjEmbedLabel(String eosUtilInjEmbedLabel) {
		this.eosUtilInjEmbedLabel = eosUtilInjEmbedLabel;
	}
	public String getEobUtilDrawalEmbedLabel() {
		return eobUtilDrawalEmbedLabel;
	}
	public void setEobUtilDrawalEmbedLabel(String eobUtilDrawalEmbedLabel) {
		this.eobUtilDrawalEmbedLabel = eobUtilDrawalEmbedLabel;
	}
	public String getEobUtilDraEmbed() {
		return eobUtilDraEmbed;
	}
	public void setEobUtilDraEmbed(String eobUtilDraEmbed) {
		this.eobUtilDraEmbed = eobUtilDraEmbed;
	}
	public String getEobHtConsumerNumber() {
		return eobHtConsumerNumber;
	}
	public void setEobHtConsumerNumber(String eobHtConsumerNumber) {
		this.eobHtConsumerNumber = eobHtConsumerNumber;
	}
	public String getEobFeederNameBuyer() {
		return eobFeederNameBuyer;
	}
	public void setEobFeederNameBuyer(String eobFeederNameBuyer) {
		this.eobFeederNameBuyer = eobFeederNameBuyer;
	}
	public String getEobVolLvlFeeder() {
		return eobVolLvlFeeder;
	}
	public void setEobVolLvlFeeder(String eobVolLvlFeeder) {
		this.eobVolLvlFeeder = eobVolLvlFeeder;
	}
	public String getEobDraSubstation() {
		return eobDraSubstation;
	}
	public void setEobDraSubstation(String eobDraSubstation) {
		this.eobDraSubstation = eobDraSubstation;
	}
	public String getEobVolLvlSubstation() {
		return eobVolLvlSubstation;
	}
	public void setEobVolLvlSubstation(String eobVolLvlSubstation) {
		this.eobVolLvlSubstation = eobVolLvlSubstation;
	}
	public Float getEobDrawalLimit() {
		return eobDrawalLimit;
	}
	public void setEobDrawalLimit(Float eobDrawalLimit) {
		this.eobDrawalLimit = eobDrawalLimit;
	}
	public String getEobBuyerType() {
		return eobBuyerType;
	}
	public void setEobBuyerType(String eobBuyerType) {
		this.eobBuyerType = eobBuyerType;
	}
	public String getEoiRegName() {
		return eoiRegName;
	}
	public void setEoiRegName(String eoiRegName) {
		this.eoiRegName = eoiRegName;
	}
	public String getEoiRegAddress() {
		return eoiRegAddress;
	}
	public void setEoiRegAddress(String eoiRegAddress) {
		this.eoiRegAddress = eoiRegAddress;
	}
	public String getEoiRegMobile1No() {
		return eoiRegMobile1No;
	}
	public void setEoiRegMobile1No(String eoiRegMobile1No) {
		this.eoiRegMobile1No = eoiRegMobile1No;
	}
	public String getEdcStatus() {
		return edcStatus;
	}
	public void setEdcStatus(String edcStatus) {
		this.edcStatus = edcStatus;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getBatchKey() {
		return batchKey;
	}
	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}
	public String getIsClean() {
		return isClean;
	}
	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}
	public String getInputRemarks() {
		return inputRemarks;
	}
	public void setInputRemarks(String inputRemarks) {
		this.inputRemarks = inputRemarks;
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
	@Override
	public String toString() {
		return "ExtSamastApplnApproval [id=" + id + ", appId=" + appId + ", applRefNo=" + applRefNo + ", applNo="
				+ applNo + ", applDate=" + applDate + ", appCategory=" + appCategory + ", customerName=" + customerName
				+ ", entityInj=" + entityInj + ", entityInjEdc=" + entityInjEdc + ", entityDrl=" + entityDrl
				+ ", entityDrlEdc=" + entityDrlEdc + ", periodStartDate=" + periodStartDate + ", periodEndDate="
				+ periodEndDate + ", quantum=" + quantum + ", category1=" + category1 + ", category2=" + category2
				+ ", eorRegType=" + eorRegType + ", eosGcApprovalNumber=" + eosGcApprovalNumber
				+ ", eosFeederNameSeller=" + eosFeederNameSeller + ", eosVolLvlFeeder=" + eosVolLvlFeeder
				+ ", eosInjSubstation=" + eosInjSubstation + ", eosVolLvlSubstation=" + eosVolLvlSubstation
				+ ", eocLossPer=" + eocLossPer + ", evacuationCapacity=" + evacuationCapacity + ", eosUtilInjEmbed="
				+ eosUtilInjEmbed + ", eosUtilInjEmbedLabel=" + eosUtilInjEmbedLabel + ", eobUtilDrawalEmbedLabel="
				+ eobUtilDrawalEmbedLabel + ", eobUtilDraEmbed=" + eobUtilDraEmbed + ", eobHtConsumerNumber="
				+ eobHtConsumerNumber + ", eobFeederNameBuyer=" + eobFeederNameBuyer + ", eobVolLvlFeeder="
				+ eobVolLvlFeeder + ", eobDraSubstation=" + eobDraSubstation + ", eobVolLvlSubstation="
				+ eobVolLvlSubstation + ", eobDrawalLimit=" + eobDrawalLimit + ", eobBuyerType=" + eobBuyerType
				+ ", eoiRegName=" + eoiRegName + ", eoiRegAddress=" + eoiRegAddress + ", eoiRegMobile1No="
				+ eoiRegMobile1No + ", edcStatus=" + edcStatus + ", applicationStatus=" + applicationStatus
				+ ", appType=" + appType + ", batchKey=" + batchKey + ", isClean=" + isClean + ", inputRemarks="
				+ inputRemarks + ", ipApprNo=" + ipApprNo + ", sellerEdcCode=" + sellerEdcCode + ", buyerEdcCode="
				+ buyerEdcCode + ", approvalNo=" + approvalNo + ", enabled=" + enabled + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}
	
	

}
