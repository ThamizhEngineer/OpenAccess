package com.ss.oa.transaction.solarLineLoss;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="T_METER_READING_HDR")
@Entity
@Scope("prototype")
public class MeterReadingHDR {
	
	@Id
	private String ID;
	
	@Column(name="M_COMPANY_METER_ID")
	private String meterId;
	
	private String STATUS_CODE;
	
	private String REMARKS;
	
	private String IMP_BATCH_ID;
	
	private String MF;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime SYS_DT;
	
	@Column(name="READING_MONTH")
	private String readingMonth;
	
	@Column(name="READING_YEAR")
	private String readingYear;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime  INIT_READING_DT;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime FINAL_READING_DT;
	
	private String TOTAL_IMPORT_GEN;
	
	private String TOTAL_EXPORT_GEN;
	
	private String CREATED_BY;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime CREATED_DATE;
	
	private String MODIFIED_BY;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime MODIFIED_DATE;
	
	private String RKVAH_DIFF;
	
	private String RKVAH_UNITS;
	
	private String KVAH_DIFF;
	
	private String KVAH_UNITS;
	
	private String IMP_RKVAH_INIT;
	
	private String EXP_RKVAH_INIT;
	
	private String IMP_RKVAH_FINAL;
	
	private String EXP_RKVAH_FINAL;
	
	private String IMP_KVAH_INIT;
	
	private String EXP_KVAH_INIT;
	
	private String IMP_KVAH_FINAL;
	
	private String EXP_KVAH_FINAL;
	
	private String NET_GEN_UNITS;
	
	private String GS_BATCH_ID;
	
	private String M_GEN_STMT_ID;
	
	@Column(columnDefinition = "char")
	private String ENABLED;
	
	private String CODE;
	
	private String IMPORT_REMARKS;
	
	@Column(columnDefinition = "char")
	private String MERGE_WITH_NEXT_BILLING;
	
	@Column(columnDefinition = "char")
	private String IMPORTED_ASIS;
	
	@Column(columnDefinition = "char")
	private String IS_METER_CHANGE;
	
    private String MR_SOURCE_CODE;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSTATUS_CODE() {
		return STATUS_CODE;
	}

	public void setSTATUS_CODE(String sTATUS_CODE) {
		STATUS_CODE = sTATUS_CODE;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public String getIMP_BATCH_ID() {
		return IMP_BATCH_ID;
	}

	public void setIMP_BATCH_ID(String iMP_BATCH_ID) {
		IMP_BATCH_ID = iMP_BATCH_ID;
	}

	public String getMF() {
		return MF;
	}

	public void setMF(String mF) {
		MF = mF;
	}

	public LocalDateTime getSYS_DT() {
		return SYS_DT;
	}

	public void setSYS_DT(LocalDateTime sYS_DT) {
		SYS_DT = sYS_DT;
	}

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public String getReadingMonth() {
		return readingMonth;
	}

	public void setReadingMonth(String readingMonth) {
		this.readingMonth = readingMonth;
	}

	public String getReadingYear() {
		return readingYear;
	}

	public void setReadingYear(String readingYear) {
		this.readingYear = readingYear;
	}

	public LocalDateTime getINIT_READING_DT() {
		return INIT_READING_DT;
	}

	public void setINIT_READING_DT(LocalDateTime iNIT_READING_DT) {
		INIT_READING_DT = iNIT_READING_DT;
	}

	public LocalDateTime getFINAL_READING_DT() {
		return FINAL_READING_DT;
	}

	public void setFINAL_READING_DT(LocalDateTime fINAL_READING_DT) {
		FINAL_READING_DT = fINAL_READING_DT;
	}

	public String getTOTAL_IMPORT_GEN() {
		return TOTAL_IMPORT_GEN;
	}

	public void setTOTAL_IMPORT_GEN(String tOTAL_IMPORT_GEN) {
		TOTAL_IMPORT_GEN = tOTAL_IMPORT_GEN;
	}

	public String getTOTAL_EXPORT_GEN() {
		return TOTAL_EXPORT_GEN;
	}

	public void setTOTAL_EXPORT_GEN(String tOTAL_EXPORT_GEN) {
		TOTAL_EXPORT_GEN = tOTAL_EXPORT_GEN;
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

	public String getRKVAH_DIFF() {
		return RKVAH_DIFF;
	}

	public void setRKVAH_DIFF(String rKVAH_DIFF) {
		RKVAH_DIFF = rKVAH_DIFF;
	}

	public String getRKVAH_UNITS() {
		return RKVAH_UNITS;
	}

	public void setRKVAH_UNITS(String rKVAH_UNITS) {
		RKVAH_UNITS = rKVAH_UNITS;
	}

	public String getKVAH_DIFF() {
		return KVAH_DIFF;
	}

	public void setKVAH_DIFF(String kVAH_DIFF) {
		KVAH_DIFF = kVAH_DIFF;
	}

	public String getKVAH_UNITS() {
		return KVAH_UNITS;
	}

	public void setKVAH_UNITS(String kVAH_UNITS) {
		KVAH_UNITS = kVAH_UNITS;
	}

	public String getIMP_RKVAH_INIT() {
		return IMP_RKVAH_INIT;
	}

	public void setIMP_RKVAH_INIT(String iMP_RKVAH_INIT) {
		IMP_RKVAH_INIT = iMP_RKVAH_INIT;
	}

	public String getEXP_RKVAH_INIT() {
		return EXP_RKVAH_INIT;
	}

	public void setEXP_RKVAH_INIT(String eXP_RKVAH_INIT) {
		EXP_RKVAH_INIT = eXP_RKVAH_INIT;
	}

	public String getIMP_RKVAH_FINAL() {
		return IMP_RKVAH_FINAL;
	}

	public void setIMP_RKVAH_FINAL(String iMP_RKVAH_FINAL) {
		IMP_RKVAH_FINAL = iMP_RKVAH_FINAL;
	}

	public String getEXP_RKVAH_FINAL() {
		return EXP_RKVAH_FINAL;
	}

	public void setEXP_RKVAH_FINAL(String eXP_RKVAH_FINAL) {
		EXP_RKVAH_FINAL = eXP_RKVAH_FINAL;
	}

	public String getIMP_KVAH_INIT() {
		return IMP_KVAH_INIT;
	}

	public void setIMP_KVAH_INIT(String iMP_KVAH_INIT) {
		IMP_KVAH_INIT = iMP_KVAH_INIT;
	}

	public String getEXP_KVAH_INIT() {
		return EXP_KVAH_INIT;
	}

	public void setEXP_KVAH_INIT(String eXP_KVAH_INIT) {
		EXP_KVAH_INIT = eXP_KVAH_INIT;
	}

	public String getIMP_KVAH_FINAL() {
		return IMP_KVAH_FINAL;
	}

	public void setIMP_KVAH_FINAL(String iMP_KVAH_FINAL) {
		IMP_KVAH_FINAL = iMP_KVAH_FINAL;
	}

	public String getEXP_KVAH_FINAL() {
		return EXP_KVAH_FINAL;
	}

	public void setEXP_KVAH_FINAL(String eXP_KVAH_FINAL) {
		EXP_KVAH_FINAL = eXP_KVAH_FINAL;
	}

	public String getNET_GEN_UNITS() {
		return NET_GEN_UNITS;
	}

	public void setNET_GEN_UNITS(String nET_GEN_UNITS) {
		NET_GEN_UNITS = nET_GEN_UNITS;
	}

	public String getGS_BATCH_ID() {
		return GS_BATCH_ID;
	}

	public void setGS_BATCH_ID(String gS_BATCH_ID) {
		GS_BATCH_ID = gS_BATCH_ID;
	}

	public String getM_GEN_STMT_ID() {
		return M_GEN_STMT_ID;
	}

	public void setM_GEN_STMT_ID(String m_GEN_STMT_ID) {
		M_GEN_STMT_ID = m_GEN_STMT_ID;
	}

	public String getENABLED() {
		return ENABLED;
	}

	public void setENABLED(String eNABLED) {
		ENABLED = eNABLED;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String cODE) {
		CODE = cODE;
	}

	public String getIMPORT_REMARKS() {
		return IMPORT_REMARKS;
	}

	public void setIMPORT_REMARKS(String iMPORT_REMARKS) {
		IMPORT_REMARKS = iMPORT_REMARKS;
	}

	public String getMERGE_WITH_NEXT_BILLING() {
		return MERGE_WITH_NEXT_BILLING;
	}

	public void setMERGE_WITH_NEXT_BILLING(String mERGE_WITH_NEXT_BILLING) {
		MERGE_WITH_NEXT_BILLING = mERGE_WITH_NEXT_BILLING;
	}

	public String getIMPORTED_ASIS() {
		return IMPORTED_ASIS;
	}

	public void setIMPORTED_ASIS(String iMPORTED_ASIS) {
		IMPORTED_ASIS = iMPORTED_ASIS;
	}

	public String getIS_METER_CHANGE() {
		return IS_METER_CHANGE;
	}

	public void setIS_METER_CHANGE(String iS_METER_CHANGE) {
		IS_METER_CHANGE = iS_METER_CHANGE;
	}

	public String getMR_SOURCE_CODE() {
		return MR_SOURCE_CODE;
	}

	public void setMR_SOURCE_CODE(String mR_SOURCE_CODE) {
		MR_SOURCE_CODE = mR_SOURCE_CODE;
	}

}
