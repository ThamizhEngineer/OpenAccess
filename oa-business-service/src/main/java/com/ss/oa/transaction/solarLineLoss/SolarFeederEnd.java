package com.ss.oa.transaction.solarLineLoss;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="AMR_TBL_SOLAR_FEEDER_END")
@Entity
@Scope("prototype")
public class SolarFeederEnd {
	
	@Id
	@Column(name="SSFEEDERID")
	private String ssFeederId;
	
	private String FEEDERNAME;
	
	private  String SECTIONNAME;
	
	@Column(name="SECTIONTYPE")
	private String sectiontype;
	
	private String SUBSTATIONNAME;
	
	private int WEGID;
	
	private String WEGNAME;
	
	private String METERNO;
	
	private String MF;
	
	private String SERVICEID;
	
	private String SERVICENO;
	
	private String VOLTAGEID;
	
	private String VOLTAGE;
	
	private int EDCID;
	
	private int GENID;
	
	private String EDCNAME;
	
	private int ORDERID;
	
	private int NOOFFEEDER;
	
	private String CIRCLENAME;
	
	private String SYS_DT;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="INITIAL_READING_DATE")
	private LocalDateTime initialReadingDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="FINAL_READING_DATE")
	private LocalDateTime finalReadingDate;
	
	private String EXP_INIT_S1;
	
	private String EXP_INIT_S2;
	
	private String EXP_INIT_S3;
	
	private String EXP_INIT_S4;
	
	private String EXP_INIT_S5;
	
	private String EXP_FINAL_S1;
	
	private String EXP_FINAL_S2;
	
	private String EXP_FINAL_S3;
	
	private String EXP_FINAL_S4;
	
	private String EXP_FINAL_S5;

	public String getFEEDERNAME() {
		return FEEDERNAME;
	}

	public void setFEEDERNAME(String fEEDERNAME) {
		FEEDERNAME = fEEDERNAME;
	}

	public String getSECTIONNAME() {
		return SECTIONNAME;
	}

	public void setSECTIONNAME(String sECTIONNAME) {
		SECTIONNAME = sECTIONNAME;
	}

	public String getSectiontype() {
		return sectiontype;
	}

	public void setSectiontype(String sectiontype) {
		this.sectiontype = sectiontype;
	}

	public String getSUBSTATIONNAME() {
		return SUBSTATIONNAME;
	}

	public void setSUBSTATIONNAME(String sUBSTATIONNAME) {
		SUBSTATIONNAME = sUBSTATIONNAME;
	}

	public int getWEGID() {
		return WEGID;
	}

	public void setWEGID(int wEGID) {
		WEGID = wEGID;
	}

	public String getWEGNAME() {
		return WEGNAME;
	}

	public void setWEGNAME(String wEGNAME) {
		WEGNAME = wEGNAME;
	}

	public String getMETERNO() {
		return METERNO;
	}

	public void setMETERNO(String mETERNO) {
		METERNO = mETERNO;
	}

	public String getMF() {
		return MF;
	}

	public void setMF(String mF) {
		MF = mF;
	}

	public String getSERVICEID() {
		return SERVICEID;
	}

	public void setSERVICEID(String sERVICEID) {
		SERVICEID = sERVICEID;
	}

	public String getSERVICENO() {
		return SERVICENO;
	}

	public void setSERVICENO(String sERVICENO) {
		SERVICENO = sERVICENO;
	}

	public String getVOLTAGEID() {
		return VOLTAGEID;
	}

	public void setVOLTAGEID(String vOLTAGEID) {
		VOLTAGEID = vOLTAGEID;
	}

	public String getVOLTAGE() {
		return VOLTAGE;
	}

	public void setVOLTAGE(String vOLTAGE) {
		VOLTAGE = vOLTAGE;
	}

	public int getEDCID() {
		return EDCID;
	}

	public void setEDCID(int eDCID) {
		EDCID = eDCID;
	}

	public int getGENID() {
		return GENID;
	}

	public void setGENID(int gENID) {
		GENID = gENID;
	}

	public String getEDCNAME() {
		return EDCNAME;
	}

	public void setEDCNAME(String eDCNAME) {
		EDCNAME = eDCNAME;
	}

	public int getORDERID() {
		return ORDERID;
	}

	public void setORDERID(int oRDERID) {
		ORDERID = oRDERID;
	}

	public int getNOOFFEEDER() {
		return NOOFFEEDER;
	}

	public void setNOOFFEEDER(int nOOFFEEDER) {
		NOOFFEEDER = nOOFFEEDER;
	}

	public String getCIRCLENAME() {
		return CIRCLENAME;
	}

	public void setCIRCLENAME(String cIRCLENAME) {
		CIRCLENAME = cIRCLENAME;
	}

	public String getSYS_DT() {
		return SYS_DT;
	}

	public void setSYS_DT(String sYS_DT) {
		SYS_DT = sYS_DT;
	}

	public LocalDateTime getInitialReadingDate() {
		return initialReadingDate;
	}

	public void setInitialReadingDate(LocalDateTime initialReadingDate) {
		this.initialReadingDate = initialReadingDate;
	}

	public LocalDateTime getFinalReadingDate() {
		return finalReadingDate;
	}

	public void setFinalReadingDate(LocalDateTime finalReadingDate) {
		this.finalReadingDate = finalReadingDate;
	}

	public String getEXP_INIT_S1() {
		return EXP_INIT_S1;
	}

	public void setEXP_INIT_S1(String eXP_INIT_S1) {
		EXP_INIT_S1 = eXP_INIT_S1;
	}

	public String getEXP_INIT_S2() {
		return EXP_INIT_S2;
	}

	public void setEXP_INIT_S2(String eXP_INIT_S2) {
		EXP_INIT_S2 = eXP_INIT_S2;
	}

	public String getEXP_INIT_S3() {
		return EXP_INIT_S3;
	}

	public void setEXP_INIT_S3(String eXP_INIT_S3) {
		EXP_INIT_S3 = eXP_INIT_S3;
	}

	public String getEXP_INIT_S4() {
		return EXP_INIT_S4;
	}

	public void setEXP_INIT_S4(String eXP_INIT_S4) {
		EXP_INIT_S4 = eXP_INIT_S4;
	}

	public String getEXP_INIT_S5() {
		return EXP_INIT_S5;
	}

	public void setEXP_INIT_S5(String eXP_INIT_S5) {
		EXP_INIT_S5 = eXP_INIT_S5;
	}

	public String getEXP_FINAL_S1() {
		return EXP_FINAL_S1;
	}

	public void setEXP_FINAL_S1(String eXP_FINAL_S1) {
		EXP_FINAL_S1 = eXP_FINAL_S1;
	}

	public String getEXP_FINAL_S2() {
		return EXP_FINAL_S2;
	}

	public void setEXP_FINAL_S2(String eXP_FINAL_S2) {
		EXP_FINAL_S2 = eXP_FINAL_S2;
	}

	public String getEXP_FINAL_S3() {
		return EXP_FINAL_S3;
	}

	public void setEXP_FINAL_S3(String eXP_FINAL_S3) {
		EXP_FINAL_S3 = eXP_FINAL_S3;
	}

	public String getEXP_FINAL_S4() {
		return EXP_FINAL_S4;
	}

	public void setEXP_FINAL_S4(String eXP_FINAL_S4) {
		EXP_FINAL_S4 = eXP_FINAL_S4;
	}

	public String getEXP_FINAL_S5() {
		return EXP_FINAL_S5;
	}

	public void setEXP_FINAL_S5(String eXP_FINAL_S5) {
		EXP_FINAL_S5 = eXP_FINAL_S5;
	}

}
