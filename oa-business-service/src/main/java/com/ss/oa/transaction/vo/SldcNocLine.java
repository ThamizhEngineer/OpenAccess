package com.ss.oa.transaction.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="T_SLDC_NOC_LINE")
@Component
public class SldcNocLine {

	@SequenceGenerator(name = "id_T_SLDC_NOC_LINE_SEQ", sequenceName = "T_SLDC_NOC_LINE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_T_SLDC_NOC_LINE_SEQ")
	@Id
	private String id;
	@Column(name = "T_SLDC_NOC_ID")
	private String tSldcNocId;
	private String compServNo;
	private String compName;
	private String commitType;
	private String fuelGroup;
	private String aggrementType;
	private LocalDate fromPeriod;
	private LocalDate toPeriod;
	private String quantum;
	private String flowType;
	private String noOfBuyer;
	private String isExisting;
	private String enabled;
	private String createdBy;
	private String customerName;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public SldcNocLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SldcNocLine(String id, String tSldcNocId, String compServNo, String compName, String commitType,
			String fuelGroup, String aggrementType, LocalDate fromPeriod, LocalDate toPeriod, String quantum,
			String flowType, String noOfBuyer, String isExisting, String enabled, String createdBy, String customerName,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.tSldcNocId = tSldcNocId;
		this.compServNo = compServNo;
		this.compName = compName;
		this.commitType = commitType;
		this.fuelGroup = fuelGroup;
		this.aggrementType = aggrementType;
		this.fromPeriod = fromPeriod;
		this.toPeriod = toPeriod;
		this.quantum = quantum;
		this.flowType = flowType;
		this.noOfBuyer = noOfBuyer;
		this.isExisting = isExisting;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.customerName = customerName;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettSldcNocId() {
		return tSldcNocId;
	}

	public void settSldcNocId(String tSldcNocId) {
		this.tSldcNocId = tSldcNocId;
	}

	public String getCompServNo() {
		return compServNo;
	}

	public void setCompServNo(String compServNo) {
		this.compServNo = compServNo;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCommitType() {
		return commitType;
	}

	public void setCommitType(String commitType) {
		this.commitType = commitType;
	}

	public String getFuelGroup() {
		return fuelGroup;
	}

	public void setFuelGroup(String fuelGroup) {
		this.fuelGroup = fuelGroup;
	}

	public String getAggrementType() {
		return aggrementType;
	}

	public void setAggrementType(String aggrementType) {
		this.aggrementType = aggrementType;
	}

	public LocalDate getFromPeriod() {
		return fromPeriod;
	}

	public void setFromPeriod(LocalDate fromPeriod) {
		this.fromPeriod = fromPeriod;
	}

	public LocalDate getToPeriod() {
		return toPeriod;
	}

	public void setToPeriod(LocalDate toPeriod) {
		this.toPeriod = toPeriod;
	}

	public String getQuantum() {
		return quantum;
	}

	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getNoOfBuyer() {
		return noOfBuyer;
	}

	public void setNoOfBuyer(String noOfBuyer) {
		this.noOfBuyer = noOfBuyer;
	}

	public String getIsExisting() {
		return isExisting;
	}

	public void setIsExisting(String isExisting) {
		this.isExisting = isExisting;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
		return "SldcNocLine [id=" + id + ", tSldcNocId=" + tSldcNocId + ", compServNo=" + compServNo + ", compName="
				+ compName + ", commitType=" + commitType + ", fuelGroup=" + fuelGroup + ", aggrementType="
				+ aggrementType + ", fromPeriod=" + fromPeriod + ", toPeriod=" + toPeriod + ", quantum=" + quantum
				+ ", flowType=" + flowType + ", noOfBuyer=" + noOfBuyer + ", isExisting=" + isExisting + ", enabled="
				+ enabled + ", createdBy=" + createdBy + ", customerName=" + customerName + ", createdDt=" + createdDt
				+ ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}

	
}
