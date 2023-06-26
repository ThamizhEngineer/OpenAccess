package com.ss.oa.oadocumentservice.vo;

public class SldcNocLine {
	
	private String id;
	private String tSldcNocId;
	private String compServNo;
	private String compName;
	private String commitType;
	private String fuelGroup;
	private String aggrementType;
	private String fromPeriod;
	private String toPeriod;
	private String quantum;
	private String flowType;
	private String noOfBuyer;
	private String createdBy;
	private String isExisting;
	private String proposed;
	private String total;
	private String customerName;
	
	public SldcNocLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SldcNocLine(String id, String tSldcNocId, String compServNo, String compName, String commitType,
			String fuelGroup, String aggrementType, String fromPeriod, String toPeriod, String quantum, String flowType,
			String noOfBuyer, String createdBy, String isExisting, String proposed, String total, String customerName) {
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
		this.createdBy = createdBy;
		this.isExisting = isExisting;
		this.proposed = proposed;
		this.total = total;
		this.customerName = customerName;
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

	public String getFromPeriod() {
		return fromPeriod;
	}

	public void setFromPeriod(String fromPeriod) {
		this.fromPeriod = fromPeriod;
	}

	public String getToPeriod() {
		return toPeriod;
	}

	public void setToPeriod(String toPeriod) {
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getIsExisting() {
		return isExisting;
	}

	public void setIsExisting(String isExisting) {
		this.isExisting = isExisting;
	}

	public String getProposed() {
		return proposed;
	}

	public void setProposed(String proposed) {
		this.proposed = proposed;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "SldcNocLine [id=" + id + ", tSldcNocId=" + tSldcNocId + ", compServNo=" + compServNo + ", compName="
				+ compName + ", commitType=" + commitType + ", fuelGroup=" + fuelGroup + ", aggrementType="
				+ aggrementType + ", fromPeriod=" + fromPeriod + ", toPeriod=" + toPeriod + ", quantum=" + quantum
				+ ", flowType=" + flowType + ", noOfBuyer=" + noOfBuyer + ", createdBy=" + createdBy + ", isExisting="
				+ isExisting + ", proposed=" + proposed + ", total=" + total + ", customerName=" + customerName + "]";
	}

	
}