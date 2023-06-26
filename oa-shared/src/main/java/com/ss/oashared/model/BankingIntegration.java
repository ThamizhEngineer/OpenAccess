package com.ss.oashared.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "T_TRANSCO_BANKING")
public class BankingIntegration {
	

	@Id
	@Column(name = "UTR")
	private String utr;
	
		@Column(name = "BENEFICIARY_ACCOUNT_NUMBER")
		private String beneficiaryAccountNumber;

		@Temporal(TemporalType.DATE)
		@Column(name = "TRANSACTION_DATE")
		private Date transactionDate;

		@Column(name = "TRANSACTION_TIME")
		private String transactionTimee;

		@Temporal(TemporalType.DATE)
		@Column(name = "VALUE_DATE")
		private Date valueDate;

		@Column(name = "VIRTUAL_ACCOUNT_NO")
		private String virtualAccountNo;

		@Column(name = "PROPOSED_ACCOUNT_TITLE")
		private String proposedAccountTitle;

		@Column(name = "SENDER_NAME")
		private String senderName;

		@Column(name = "REMITTER_NAME_RBI")
		private String remitterNameRBI;

		@Column(name = "SENDER_ACCOUNT_NUMBER")
		private String senderAccountNumber;

		@Column(name = "SENDER_BRANCH_IFSC")
		private String senderBranchIFSC;

		

		@Column(name = "TRANSACTION_TYPE")
		private String mode;

		@Column(name = "DEBIT_CREDIT")
		private String debitCredit;

		@Column(name = "TRAN_AMT")
		private BigDecimal tranAmt;

		@Column(name = "BILL_MONTH")
		private Integer billMonth;

		@Column(name = "BILL_YEAR")
		private Integer billYear;

		@Column(name = "RECEIPT_NO")
		private String receiptNo;
		
		@Column(name = "BANKTRANSACTIONID")
		private String banktranid;
		
		@Column(name = "BANKNAME")
		private String bankname;

		public String getUtr() {
			return utr;
		}

		public void setUtr(String utr) {
			this.utr = utr;
		}

		public String getBeneficiaryAccountNumber() {
			return beneficiaryAccountNumber;
		}

		public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
			this.beneficiaryAccountNumber = beneficiaryAccountNumber;
		}

		public Date getTransactionDate() {
			return transactionDate;
		}

		public void setTransactionDate(Date transactionDate) {
			this.transactionDate = transactionDate;
		}

		public String getTransactionTimee() {
			return transactionTimee;
		}

		public void setTransactionTimee(String transactionTimee) {
			this.transactionTimee = transactionTimee;
		}

		public Date getValueDate() {
			return valueDate;
		}

		public void setValueDate(Date valueDate) {
			this.valueDate = valueDate;
		}

		public String getVirtualAccountNo() {
			return virtualAccountNo;
		}

		public void setVirtualAccountNo(String virtualAccountNo) {
			this.virtualAccountNo = virtualAccountNo;
		}

		public String getProposedAccountTitle() {
			return proposedAccountTitle;
		}

		public void setProposedAccountTitle(String proposedAccountTitle) {
			this.proposedAccountTitle = proposedAccountTitle;
		}

		public String getSenderName() {
			return senderName;
		}

		public void setSenderName(String senderName) {
			this.senderName = senderName;
		}

		public String getRemitterNameRBI() {
			return remitterNameRBI;
		}

		public void setRemitterNameRBI(String remitterNameRBI) {
			this.remitterNameRBI = remitterNameRBI;
		}

		public String getSenderAccountNumber() {
			return senderAccountNumber;
		}

		public void setSenderAccountNumber(String senderAccountNumber) {
			this.senderAccountNumber = senderAccountNumber;
		}

		public String getSenderBranchIFSC() {
			return senderBranchIFSC;
		}

		public void setSenderBranchIFSC(String senderBranchIFSC) {
			this.senderBranchIFSC = senderBranchIFSC;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getDebitCredit() {
			return debitCredit;
		}

		public void setDebitCredit(String debitCredit) {
			this.debitCredit = debitCredit;
		}

		public BigDecimal getTranAmt() {
			return tranAmt;
		}

		public void setTranAmt(BigDecimal tranAmt) {
			this.tranAmt = tranAmt;
		}

		public Integer getBillMonth() {
			return billMonth;
		}

		public void setBillMonth(Integer billMonth) {
			this.billMonth = billMonth;
		}

		public Integer getBillYear() {
			return billYear;
		}

		public void setBillYear(Integer billYear) {
			this.billYear = billYear;
		}

		public String getReceiptNo() {
			return receiptNo;
		}

		public void setReceiptNo(String receiptNo) {
			this.receiptNo = receiptNo;
		}

		public String getBanktranid() {
			return banktranid;
		}

		public void setBanktranid(String banktranid) {
			this.banktranid = banktranid;
		}

		public String getBankname() {
			return bankname;
		}

		public void setBankname(String bankname) {
			this.bankname = bankname;
		}

		@Override
		public String toString() {
			return "BankingIntegration [utr=" + utr + ", beneficiaryAccountNumber=" + beneficiaryAccountNumber
					+ ", transactionDate=" + transactionDate + ", transactionTimee=" + transactionTimee + ", valueDate="
					+ valueDate + ", virtualAccountNo=" + virtualAccountNo + ", proposedAccountTitle="
					+ proposedAccountTitle + ", senderName=" + senderName + ", remitterNameRBI=" + remitterNameRBI
					+ ", senderAccountNumber=" + senderAccountNumber + ", senderBranchIFSC=" + senderBranchIFSC
					+ ", mode=" + mode + ", debitCredit=" + debitCredit + ", tranAmt=" + tranAmt + ", billMonth="
					+ billMonth + ", billYear=" + billYear + ", receiptNo=" + receiptNo + ", banktranid=" + banktranid
					+ ", bankname=" + bankname + ", getUtr()=" + getUtr() + ", getBeneficiaryAccountNumber()="
					+ getBeneficiaryAccountNumber() + ", getTransactionDate()=" + getTransactionDate()
					+ ", getTransactionTime()=" + getTransactionTimee() + ", getValueDate()=" + getValueDate()
					+ ", getVirtualAccountNo()=" + getVirtualAccountNo() + ", getProposedAccountTitle()="
					+ getProposedAccountTitle() + ", getSenderName()=" + getSenderName() + ", getRemitterNameRBI()="
					+ getRemitterNameRBI() + ", getSenderAccountNumber()=" + getSenderAccountNumber()
					+ ", getSenderBranchIFSC()=" + getSenderBranchIFSC() + ", getMode()=" + getMode()
					+ ", getDebitCredit()=" + getDebitCredit() + ", getTranAmt()=" + getTranAmt() + ", getBillMonth()="
					+ getBillMonth() + ", getBillYear()=" + getBillYear() + ", getReceiptNo()=" + getReceiptNo()
					+ ", getBanktranid()=" + getBanktranid() + ", getBankname()=" + getBankname() + ", getClass()="
					+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

		public BankingIntegration(String utr, String beneficiaryAccountNumber, Date transactionDate,
				String transactionTimee, Date valueDate, String virtualAccountNo, String proposedAccountTitle,
				String senderName, String remitterNameRBI, String senderAccountNumber, String senderBranchIFSC,
				String mode, String debitCredit, BigDecimal tranAmt, Integer billMonth, Integer billYear,
				String receiptNo, String banktranid, String bankname) {
			super();
			this.utr = utr;
			this.beneficiaryAccountNumber = beneficiaryAccountNumber;
			this.transactionDate = transactionDate;
			this.transactionTimee = transactionTimee;
			this.valueDate = valueDate;
			this.virtualAccountNo = virtualAccountNo;
			this.proposedAccountTitle = proposedAccountTitle;
			this.senderName = senderName;
			this.remitterNameRBI = remitterNameRBI;
			this.senderAccountNumber = senderAccountNumber;
			this.senderBranchIFSC = senderBranchIFSC;
			this.mode = mode;
			this.debitCredit = debitCredit;
			this.tranAmt = tranAmt;
			this.billMonth = billMonth;
			this.billYear = billYear;
			this.receiptNo = receiptNo;
			this.banktranid = banktranid;
			this.bankname = bankname;
		}

		public BankingIntegration() {
			super();
			// TODO Auto-generated constructor stub
		}
		
			

}
