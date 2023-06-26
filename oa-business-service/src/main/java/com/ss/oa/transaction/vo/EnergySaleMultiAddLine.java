package com.ss.oa.transaction.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="T_ES_MULTIADD_LINE")
@CreationTimestamp @UpdateTimestamp
@Entity
public class EnergySaleMultiAddLine {
	
	
	@Id
	private String id;
	@Column(name="T_ES_MULTIADD_HEADER_ID")
	private String energySaleMultiAddHeaderId;
	@Column(name="M_TRADEREL_ID")
	private String tradeRelationshipId;
	@Column(name="M_COMP_SERV_NUMBER")
	private String companyServiceNumber;
	@Column(name="T_ES_USAGE_SUMMARY_ID")
	private String energySaleUsageSummaryId;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String c001;
	private String c002;
	private String c003;
	private String c004;
	private String c005;
	private String c006;
	private String c007;
	private String c008;
	private String c009;
	@Column(columnDefinition = "char")
	private String isClean;
	@Column(columnDefinition = "char")
	private String imported;
	@Column(name="M_COMP_SERV_ID")
	private String companyServiceId;	
	private String importRemarks;
	private String remarks;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDt;
	@Column(columnDefinition = "char")
	private String enabled;
	
	public EnergySaleMultiAddLine() {
		super();
	}

	public EnergySaleMultiAddLine(String id, String energySaleMultiAddHeaderId, String tradeRelationshipId,
			String companyServiceNumber, String energySaleUsageSummaryId, String c1, String c2, String c3, String c4,
			String c5, String c001, String c002, String c003, String c004, String c005, String c006, String c007,
			String c008, String c009, String isClean, String imported, String companyServiceId, String importRemarks,
			String remarks, LocalDate createdDt, String modifiedBy, LocalDate modifiedDt, String enabled) {
		super();
		this.id = id;
		this.energySaleMultiAddHeaderId = energySaleMultiAddHeaderId;
		this.tradeRelationshipId = tradeRelationshipId;
		this.companyServiceNumber = companyServiceNumber;
		this.energySaleUsageSummaryId = energySaleUsageSummaryId;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.c001 = c001;
		this.c002 = c002;
		this.c003 = c003;
		this.c004 = c004;
		this.c005 = c005;
		this.c006 = c006;
		this.c007 = c007;
		this.c008 = c008;
		this.c009 = c009;
		this.isClean = isClean;
		this.imported = imported;
		this.companyServiceId = companyServiceId;
		this.importRemarks = importRemarks;
		this.remarks = remarks;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "EnergySaleMultiAddLine [id=" + id + ", energySaleMultiAddHeaderId=" + energySaleMultiAddHeaderId
				+ ", tradeRelationshipId=" + tradeRelationshipId + ", companyServiceNumber=" + companyServiceNumber
				+ ", energySaleUsageSummaryId=" + energySaleUsageSummaryId + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3
				+ ", c4=" + c4 + ", c5=" + c5 + ", c001=" + c001 + ", c002=" + c002 + ", c003=" + c003 + ", c004="
				+ c004 + ", c005=" + c005 + ", c006=" + c006 + ", c007=" + c007 + ", c008=" + c008 + ", c009=" + c009
				+ ", isClean=" + isClean + ", imported=" + imported + ", companyServiceId=" + companyServiceId
				+ ", importRemarks=" + importRemarks + ", remarks=" + remarks + ", createdDt=" + createdDt
				+ ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + ", enabled=" + enabled + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnergySaleMultiAddHeaderId() {
		return energySaleMultiAddHeaderId;
	}

	public void setEnergySaleMultiAddHeaderId(String energySaleMultiAddHeaderId) {
		this.energySaleMultiAddHeaderId = energySaleMultiAddHeaderId;
	}

	public String getTradeRelationshipId() {
		return tradeRelationshipId;
	}

	public void setTradeRelationshipId(String tradeRelationshipId) {
		this.tradeRelationshipId = tradeRelationshipId;
	}

	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}

	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
	}

	public String getEnergySaleUsageSummaryId() {
		return energySaleUsageSummaryId;
	}

	public void setEnergySaleUsageSummaryId(String energySaleUsageSummaryId) {
		this.energySaleUsageSummaryId = energySaleUsageSummaryId;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getC001() {
		return c001;
	}

	public void setC001(String c001) {
		this.c001 = c001;
	}

	public String getC002() {
		return c002;
	}

	public void setC002(String c002) {
		this.c002 = c002;
	}

	public String getC003() {
		return c003;
	}

	public void setC003(String c003) {
		this.c003 = c003;
	}

	public String getC004() {
		return c004;
	}

	public void setC004(String c004) {
		this.c004 = c004;
	}

	public String getC005() {
		return c005;
	}

	public void setC005(String c005) {
		this.c005 = c005;
	}

	public String getC006() {
		return c006;
	}

	public void setC006(String c006) {
		this.c006 = c006;
	}

	public String getC007() {
		return c007;
	}

	public void setC007(String c007) {
		this.c007 = c007;
	}

	public String getC008() {
		return c008;
	}

	public void setC008(String c008) {
		this.c008 = c008;
	}

	public String getC009() {
		return c009;
	}

	public void setC009(String c009) {
		this.c009 = c009;
	}

	public String getIsClean() {
		return isClean;
	}

	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}

	public String getImported() {
		return imported;
	}

	public void setImported(String imported) {
		this.imported = imported;
	}

	public String getCompanyServiceId() {
		return companyServiceId;
	}

	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDate getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDate createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDate modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	

	

}
