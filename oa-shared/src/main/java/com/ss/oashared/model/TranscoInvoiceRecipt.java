package com.ss.oashared.model;

public class TranscoInvoiceRecipt {
	
	private String ReciptNo;
	private String ReciptMonth;
	private String ReciptYear;
	private String GeneratorNo;
	private String GeneratorName;
	private String ViritualAccNo;
	private String AmountPaid;
	private	String BankTransactionDetails;
	private String docPath;
	private String fileName;
	private String fileNameToUser;
	private String fileExtension;
	private String docId;
	public String getReciptNo() {
		return ReciptNo;
	}
	public void setReciptNo(String reciptNo) {
		ReciptNo = reciptNo;
	}
	public String getReciptMonth() {
		return ReciptMonth;
	}
	public void setReciptMonth(String reciptMonth) {
		ReciptMonth = reciptMonth;
	}
	public String getReciptYear() {
		return ReciptYear;
	}
	public void setReciptYear(String reciptYear) {
		ReciptYear = reciptYear;
	}
	public String getGeneratorNo() {
		return GeneratorNo;
	}
	public void setGeneratorNo(String generatorNo) {
		GeneratorNo = generatorNo;
	}
	public String getGeneratorName() {
		return GeneratorName;
	}
	public void setGeneratorName(String generatorName) {
		GeneratorName = generatorName;
	}
	public String getAmountPaid() {
		return AmountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		AmountPaid = amountPaid;
	}
	public String getBankTransactionDetails() {
		return BankTransactionDetails;
	}
	public void setBankTransactionDetails(String bankTransactionDetails) {
		BankTransactionDetails = bankTransactionDetails;
	}
	@Override
	public String toString() {
		return "transcoInvoiceRecipt [ReciptNo=" + ReciptNo + ", ReciptMonth=" + ReciptMonth + ", ReciptYear="
				+ ReciptYear + ", GeneratorNo=" + GeneratorNo + ", GeneratorName=" + GeneratorName + ", AmountPaid="
				+ AmountPaid + ", BankTransactionDetails=" + BankTransactionDetails + ", getReciptNo()=" + getReciptNo()
				+ ", getReciptMonth()=" + getReciptMonth() + ", getReciptYear()=" + getReciptYear()
				+ ", getGeneratorNo()=" + getGeneratorNo() + ", getGeneratorName()=" + getGeneratorName()
				+ ", getAmountPaid()=" + getAmountPaid() + ", getBankTransactionDetails()="
				+ getBankTransactionDetails() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileNameToUser() {
		return fileNameToUser;
	}
	public void setFileNameToUser(String fileNameToUser) {
		this.fileNameToUser = fileNameToUser;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getViritualAccNo() {
		return ViritualAccNo;
	}
	public void setViritualAccNo(String viritualAccNo) {
		ViritualAccNo = viritualAccNo;
	}
	
	
	

}
