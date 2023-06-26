package com.ss.oa.transaction.vo;

public class Payment {
	private String id,code;
	private String fromCompanyId, fromCompanyCode, fromCompanyName;
	private String fromCompanyServiceId,fromCompanyServiceNumber, fromCompanyOrgId, fromCompanyOrgCode, fromCompanyOrgName; 
	private String fromCompanyFeederId, fromCompanyFeederCode, fromCompanyFeederName,fromCompanySubstationId, fromCompanySubstationCode, fromCompanySubstationName;
	private String toCompanyId, toCompanyCode, toCompanyName;
	private String toCompanyServiceId,toCompanyServiceNumber, toCompanyOrgId, toCompanyOrgCode, toCompanyOrgName; 
	private String toCompanyFeederId, toCompanyFeederCode, toCompanyFeederName,toCompanySubstationId, toCompanySubstationCode, toCompanySubstationName;
	private String paymentContextCode,contextRefNumber;
	private String invoiceNumber,invoiceDate;
	private String bankName,branchName;
	private String modeOfPayment,instrumentNumber,paymentDate,amount,remarks;

	
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Payment(String id, String code, String fromCompanyId, String fromCompanyCode,
			String fromCompanyName, String fromCompanyServiceId, String fromCompanyServiceNumber,
			String fromCompanyOrgId, String fromCompanyOrgCode, String fromCompanyOrgName, String fromCompanyFeederId,
			String fromCompanyFeederCode, String fromCompanyFeederName, String fromCompanySubstationId,
			String fromCompanySubstationCode, String fromCompanySubstationName, String toCompanyId,
			String toCompanyCode, String toCompanyName, String toCompanyServiceId, String toCompanyServiceNumber,
			String toCompanyOrgId, String toCompanyOrgCode, String toCompanyOrgName, String toCompanyFeederId,
			String toCompanyFeederCode, String toCompanyFeederName, String toCompanySubstationId,
			String toCompanySubstationCode, String toCompanySubstationName, String paymentContextCode,
			String contextRefNumber, String invoiceNumber, String invoiceDate, String bankName, String branchName,
			String modeOfPayment, String instrumentNumber, String paymentDate, String amount, String remarks) {
		super();
		this.id = id;
		this.code = code;
		this.fromCompanyId = fromCompanyId;
		this.fromCompanyCode = fromCompanyCode;
		this.fromCompanyName = fromCompanyName;
		this.fromCompanyServiceId = fromCompanyServiceId;
		this.fromCompanyServiceNumber = fromCompanyServiceNumber;
		this.fromCompanyOrgId = fromCompanyOrgId;
		this.fromCompanyOrgCode = fromCompanyOrgCode;
		this.fromCompanyOrgName = fromCompanyOrgName;
		this.fromCompanyFeederId = fromCompanyFeederId;
		this.fromCompanyFeederCode = fromCompanyFeederCode;
		this.fromCompanyFeederName = fromCompanyFeederName;
		this.fromCompanySubstationId = fromCompanySubstationId;
		this.fromCompanySubstationCode = fromCompanySubstationCode;
		this.fromCompanySubstationName = fromCompanySubstationName;
		this.toCompanyId = toCompanyId;
		this.toCompanyCode = toCompanyCode;
		this.toCompanyName = toCompanyName;
		this.toCompanyServiceId = toCompanyServiceId;
		this.toCompanyServiceNumber = toCompanyServiceNumber;
		this.toCompanyOrgId = toCompanyOrgId;
		this.toCompanyOrgCode = toCompanyOrgCode;
		this.toCompanyOrgName = toCompanyOrgName;
		this.toCompanyFeederId = toCompanyFeederId;
		this.toCompanyFeederCode = toCompanyFeederCode;
		this.toCompanyFeederName = toCompanyFeederName;
		this.toCompanySubstationId = toCompanySubstationId;
		this.toCompanySubstationCode = toCompanySubstationCode;
		this.toCompanySubstationName = toCompanySubstationName;
		this.paymentContextCode = paymentContextCode;
		this.contextRefNumber = contextRefNumber;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.bankName = bankName;
		this.branchName = branchName;
		this.modeOfPayment = modeOfPayment;
		this.instrumentNumber = instrumentNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.remarks = remarks;
	}



	@Override
	public String toString() {
		return "Payment [id=" + id + ", code=" + code + ", fromCompanyId=" + fromCompanyId
				+ ", fromCompanyCode=" + fromCompanyCode + ", fromCompanyName=" + fromCompanyName
				+ ", fromCompanyServiceId=" + fromCompanyServiceId + ", fromCompanyServiceNumber="
				+ fromCompanyServiceNumber + ", fromCompanyOrgId=" + fromCompanyOrgId + ", fromCompanyOrgCode="
				+ fromCompanyOrgCode + ", fromCompanyOrgName=" + fromCompanyOrgName + ", fromCompanyFeederId="
				+ fromCompanyFeederId + ", fromCompanyFeederCode=" + fromCompanyFeederCode + ", fromCompanyFeederName="
				+ fromCompanyFeederName + ", fromCompanySubstationId=" + fromCompanySubstationId
				+ ", fromCompanySubstationCode=" + fromCompanySubstationCode + ", fromCompanySubstationName="
				+ fromCompanySubstationName + ", toCompanyId=" + toCompanyId + ", toCompanyCode=" + toCompanyCode
				+ ", toCompanyName=" + toCompanyName + ", toCompanyServiceId=" + toCompanyServiceId
				+ ", toCompanyServiceNumber=" + toCompanyServiceNumber + ", toCompanyOrgId=" + toCompanyOrgId
				+ ", toCompanyOrgCode=" + toCompanyOrgCode + ", toCompanyOrgName=" + toCompanyOrgName
				+ ", toCompanyFeederId=" + toCompanyFeederId + ", toCompanyFeederCode=" + toCompanyFeederCode
				+ ", toCompanyFeederName=" + toCompanyFeederName + ", toCompanySubstationId=" + toCompanySubstationId
				+ ", toCompanySubstationCode=" + toCompanySubstationCode + ", toCompanySubstationName="
				+ toCompanySubstationName + ", paymentContextCode=" + paymentContextCode + ", contextRefNumber="
				+ contextRefNumber + ", invoiceNumber=" + invoiceNumber + ", invoiceDate=" + invoiceDate + ", bankName="
				+ bankName + ", branchName=" + branchName + ", modeOfPayment=" + modeOfPayment + ", instrumentNumber="
				+ instrumentNumber + ", paymentDate=" + paymentDate + ", amount=" + amount + ", remarks=" + remarks
				+ "]";
	}



	public String getId() {
		return id;
	}



	public String getCode() {
		return code;
	}



	public String getFromCompanyId() {
		return fromCompanyId;
	}



	public String getFromCompanyCode() {
		return fromCompanyCode;
	}



	public String getFromCompanyName() {
		return fromCompanyName;
	}



	public String getFromCompanyServiceId() {
		return fromCompanyServiceId;
	}



	public String getFromCompanyServiceNumber() {
		return fromCompanyServiceNumber;
	}



	public String getFromCompanyOrgId() {
		return fromCompanyOrgId;
	}



	public String getFromCompanyOrgCode() {
		return fromCompanyOrgCode;
	}



	public String getFromCompanyOrgName() {
		return fromCompanyOrgName;
	}



	public String getFromCompanyFeederId() {
		return fromCompanyFeederId;
	}



	public String getFromCompanyFeederCode() {
		return fromCompanyFeederCode;
	}



	public String getFromCompanyFeederName() {
		return fromCompanyFeederName;
	}



	public String getFromCompanySubstationId() {
		return fromCompanySubstationId;
	}



	public String getFromCompanySubstationCode() {
		return fromCompanySubstationCode;
	}



	public String getFromCompanySubstationName() {
		return fromCompanySubstationName;
	}



	public String getToCompanyId() {
		return toCompanyId;
	}



	public String getToCompanyCode() {
		return toCompanyCode;
	}



	public String getToCompanyName() {
		return toCompanyName;
	}



	public String getToCompanyServiceId() {
		return toCompanyServiceId;
	}



	public String getToCompanyServiceNumber() {
		return toCompanyServiceNumber;
	}



	public String getToCompanyOrgId() {
		return toCompanyOrgId;
	}



	public String getToCompanyOrgCode() {
		return toCompanyOrgCode;
	}



	public String getToCompanyOrgName() {
		return toCompanyOrgName;
	}



	public String getToCompanyFeederId() {
		return toCompanyFeederId;
	}



	public String getToCompanyFeederCode() {
		return toCompanyFeederCode;
	}



	public String getToCompanyFeederName() {
		return toCompanyFeederName;
	}



	public String getToCompanySubstationId() {
		return toCompanySubstationId;
	}



	public String getToCompanySubstationCode() {
		return toCompanySubstationCode;
	}



	public String getToCompanySubstationName() {
		return toCompanySubstationName;
	}



	public String getPaymentContextCode() {
		return paymentContextCode;
	}



	public String getContextRefNumber() {
		return contextRefNumber;
	}



	public String getInvoiceNumber() {
		return invoiceNumber;
	}



	public String getInvoiceDate() {
		return invoiceDate;
	}



	public String getBankName() {
		return bankName;
	}



	public String getBranchName() {
		return branchName;
	}



	public String getModeOfPayment() {
		return modeOfPayment;
	}



	public String getInstrumentNumber() {
		return instrumentNumber;
	}



	public String getPaymentDate() {
		return paymentDate;
	}



	public String getAmount() {
		return amount;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setId(String id) {
		this.id = id;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public void setFromCompanyId(String fromCompanyId) {
		this.fromCompanyId = fromCompanyId;
	}



	public void setFromCompanyCode(String fromCompanyCode) {
		this.fromCompanyCode = fromCompanyCode;
	}



	public void setFromCompanyName(String fromCompanyName) {
		this.fromCompanyName = fromCompanyName;
	}



	public void setFromCompanyServiceId(String fromCompanyServiceId) {
		this.fromCompanyServiceId = fromCompanyServiceId;
	}



	public void setFromCompanyServiceNumber(String fromCompanyServiceNumber) {
		this.fromCompanyServiceNumber = fromCompanyServiceNumber;
	}



	public void setFromCompanyOrgId(String fromCompanyOrgId) {
		this.fromCompanyOrgId = fromCompanyOrgId;
	}



	public void setFromCompanyOrgCode(String fromCompanyOrgCode) {
		this.fromCompanyOrgCode = fromCompanyOrgCode;
	}



	public void setFromCompanyOrgName(String fromCompanyOrgName) {
		this.fromCompanyOrgName = fromCompanyOrgName;
	}



	public void setFromCompanyFeederId(String fromCompanyFeederId) {
		this.fromCompanyFeederId = fromCompanyFeederId;
	}



	public void setFromCompanyFeederCode(String fromCompanyFeederCode) {
		this.fromCompanyFeederCode = fromCompanyFeederCode;
	}



	public void setFromCompanyFeederName(String fromCompanyFeederName) {
		this.fromCompanyFeederName = fromCompanyFeederName;
	}



	public void setFromCompanySubstationId(String fromCompanySubstationId) {
		this.fromCompanySubstationId = fromCompanySubstationId;
	}



	public void setFromCompanySubstationCode(String fromCompanySubstationCode) {
		this.fromCompanySubstationCode = fromCompanySubstationCode;
	}



	public void setFromCompanySubstationName(String fromCompanySubstationName) {
		this.fromCompanySubstationName = fromCompanySubstationName;
	}



	public void setToCompanyId(String toCompanyId) {
		this.toCompanyId = toCompanyId;
	}



	public void setToCompanyCode(String toCompanyCode) {
		this.toCompanyCode = toCompanyCode;
	}



	public void setToCompanyName(String toCompanyName) {
		this.toCompanyName = toCompanyName;
	}



	public void setToCompanyServiceId(String toCompanyServiceId) {
		this.toCompanyServiceId = toCompanyServiceId;
	}



	public void setToCompanyServiceNumber(String toCompanyServiceNumber) {
		this.toCompanyServiceNumber = toCompanyServiceNumber;
	}



	public void setToCompanyOrgId(String toCompanyOrgId) {
		this.toCompanyOrgId = toCompanyOrgId;
	}



	public void setToCompanyOrgCode(String toCompanyOrgCode) {
		this.toCompanyOrgCode = toCompanyOrgCode;
	}



	public void setToCompanyOrgName(String toCompanyOrgName) {
		this.toCompanyOrgName = toCompanyOrgName;
	}



	public void setToCompanyFeederId(String toCompanyFeederId) {
		this.toCompanyFeederId = toCompanyFeederId;
	}



	public void setToCompanyFeederCode(String toCompanyFeederCode) {
		this.toCompanyFeederCode = toCompanyFeederCode;
	}



	public void setToCompanyFeederName(String toCompanyFeederName) {
		this.toCompanyFeederName = toCompanyFeederName;
	}



	public void setToCompanySubstationId(String toCompanySubstationId) {
		this.toCompanySubstationId = toCompanySubstationId;
	}



	public void setToCompanySubstationCode(String toCompanySubstationCode) {
		this.toCompanySubstationCode = toCompanySubstationCode;
	}



	public void setToCompanySubstationName(String toCompanySubstationName) {
		this.toCompanySubstationName = toCompanySubstationName;
	}



	public void setPaymentContextCode(String paymentContextCode) {
		this.paymentContextCode = paymentContextCode;
	}



	public void setContextRefNumber(String contextRefNumber) {
		this.contextRefNumber = contextRefNumber;
	}



	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}



	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}



	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}



	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}



	public void setInstrumentNumber(String instrumentNumber) {
		this.instrumentNumber = instrumentNumber;
	}



	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}



	public void setAmount(String amount) {
		this.amount = amount;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	
	

}
