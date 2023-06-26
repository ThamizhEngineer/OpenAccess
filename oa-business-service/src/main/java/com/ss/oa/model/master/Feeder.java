package com.ss.oa.model.master;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="M_FEEDER")
@Entity
@Scope("prototype")
public class Feeder {
	
	@Id
	private String ID;
	
	private String CODE;
	
	private String NAME;
	
	private String VOLTAGE;
	
	private String M_SUBSTATION_ID;
	
	private String REMARKS;
	
	@Column(columnDefinition = "char")
	private String ENABLED;
	
	private String SS_NAME;
	
	private String CREATED_BY;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime CREATED_DATE;
	
	private String MODIFIED_BY;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime MODIFIED_DATE;
	
	private String VOLTAGE_CODE;
	
	private String FEEDER_LINE_LENGTH;
	
	private String METERNO;
	

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String cODE) {
		CODE = cODE;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getVOLTAGE() {
		return VOLTAGE;
	}

	public void setVOLTAGE(String vOLTAGE) {
		VOLTAGE = vOLTAGE;
	}

	public String getM_SUBSTATION_ID() {
		return M_SUBSTATION_ID;
	}

	public void setM_SUBSTATION_ID(String m_SUBSTATION_ID) {
		M_SUBSTATION_ID = m_SUBSTATION_ID;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public String getENABLED() {
		return ENABLED;
	}

	public void setENABLED(String eNABLED) {
		ENABLED = eNABLED;
	}

	public String getSS_NAME() {
		return SS_NAME;
	}

	public void setSS_NAME(String sS_NAME) {
		SS_NAME = sS_NAME;
	}

	public String getCREATED_BY() {
		return CREATED_BY;
	}

	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}

	public LocalDateTime getCREATED_DATE() {
		return CREATED_DATE;
	}

	public void setCREATED_DATE(LocalDateTime cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public String getMODIFIED_BY() {
		return MODIFIED_BY;
	}

	public void setMODIFIED_BY(String mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
	}

	public LocalDateTime getMODIFIED_DATE() {
		return MODIFIED_DATE;
	}

	public void setMODIFIED_DATE(LocalDateTime mODIFIED_DATE) {
		MODIFIED_DATE = mODIFIED_DATE;
	}

	public String getVOLTAGE_CODE() {
		return VOLTAGE_CODE;
	}

	public void setVOLTAGE_CODE(String vOLTAGE_CODE) {
		VOLTAGE_CODE = vOLTAGE_CODE;
	}

	public String getFEEDER_LINE_LENGTH() {
		return FEEDER_LINE_LENGTH;
	}

	public void setFEEDER_LINE_LENGTH(String fEEDER_LINE_LENGTH) {
		FEEDER_LINE_LENGTH = fEEDER_LINE_LENGTH;
	}

	public String getMETERNO() {
		return METERNO;
	}

	public void setMETERNO(String mETERNO) {
		METERNO = mETERNO;
	}

}
