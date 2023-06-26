package com.ss.oa.vo;

import java.util.Date;

public class QrcodeInfoData {
	private Integer BILL_MNTH;  
	private Integer BILL_YR;
	private String INVOICE_NO; 
	private String IRN;       
	private String ACK_NO;     
	private String QRCODE;     
	private Byte[] QRIMG;     
	private String EWB_NUMBER; 
	private String IRN_STATUS ;
	private String EWB_STATUS ;
	private Date CREATE_DATE;
	private Date EWB_DATE;   
	private String ACK_DATE;   
	private String MESSAGE;    
	private String SERVICENO;
	public Integer getBILL_MNTH() {
		return BILL_MNTH;
	}
	public void setBILL_MNTH(Integer bILL_MNTH) {
		BILL_MNTH = bILL_MNTH;
	}
	public Integer getBILL_YR() {
		return BILL_YR;
	}
	public void setBILL_YR(Integer bILL_YR) {
		BILL_YR = bILL_YR;
	}
	public String getINVOICE_NO() {
		return INVOICE_NO;
	}
	public void setINVOICE_NO(String iNVOICE_NO) {
		INVOICE_NO = iNVOICE_NO;
	}
	public String getIRN() {
		return IRN;
	}
	public void setIRN(String iRN) {
		IRN = iRN;
	}
	public String getACK_NO() {
		return ACK_NO;
	}
	public void setACK_NO(String aCK_NO) {
		ACK_NO = aCK_NO;
	}
	public String getQRCODE() {
		return QRCODE;
	}
	public void setQRCODE(String qRCODE) {
		QRCODE = qRCODE;
	}
	public Byte[] getQRIMG() {
		return QRIMG;
	}
	public void setQRIMG(Byte[] qRIMG) {
		QRIMG = qRIMG;
	}
	public String getEWB_NUMBER() {
		return EWB_NUMBER;
	}
	public void setEWB_NUMBER(String eWB_NUMBER) {
		EWB_NUMBER = eWB_NUMBER;
	}
	public String getIRN_STATUS() {
		return IRN_STATUS;
	}
	public void setIRN_STATUS(String iRN_STATUS) {
		IRN_STATUS = iRN_STATUS;
	}
	public String getEWB_STATUS() {
		return EWB_STATUS;
	}
	public void setEWB_STATUS(String eWB_STATUS) {
		EWB_STATUS = eWB_STATUS;
	}
	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}
	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}
	public Date getEWB_DATE() {
		return EWB_DATE;
	}
	public void setEWB_DATE(Date eWB_DATE) {
		EWB_DATE = eWB_DATE;
	}
	public String getACK_DATE() {
		return ACK_DATE;
	}
	public void setACK_DATE(String aCK_DATE) {
		ACK_DATE = aCK_DATE;
	}
	public String getMESSAGE() {
		return MESSAGE;
	}
	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}
	public String getSERVICENO() {
		return SERVICENO;
	}
	public void setSERVICENO(String sERVICENO) {
		SERVICENO = sERVICENO;
	}
	
	
	
	
}
