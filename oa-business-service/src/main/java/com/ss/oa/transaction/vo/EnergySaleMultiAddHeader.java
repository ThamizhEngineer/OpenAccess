package com.ss.oa.transaction.vo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="T_ES_MULTIADD_HEADER")
@CreationTimestamp @UpdateTimestamp
@Entity
public class EnergySaleMultiAddHeader {
	
	@Id
	private String id;
	@Column(name="T_ES_ID")
	private String energySaleId;
	@Column(name="T_GEN_STMT_ID")
	private String generationStatementId;
	private String batchId;
	private Integer totalCount;
	private Integer successCount;
	private Integer errorCount;
	private String remarks;
	@Column(name="SELLER_COMP_SERV_ID")
	private String sellerCompanyServiceId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDt;
	@Column(name="TOTAL_C1")
	private String totalC1;
	@Column(name="TOTAL_C2")
	private String totalC2;
	@Column(name="TOTAL_C3")
	private String totalC3;
	@Column(name="TOTAL_C4")
	private String totalC4;
	@Column(name="TOTAL_C5")
	private String totalC5;
	@Column(name="TOTAL_C001")
	private String totalC001;
	@Column(name="TOTAL_C002")
	private String totalC002;
	@Column(name="TOTAL_C003")
	private String totalC003;
	@Column(name="TOTAL_C004")
	private String totalC004;
	@Column(name="TOTAL_C005")
	private String totalC005;
	@Column(name="TOTAL_C006")
	private String totalC006;
	@Column(name="TOTAL_C007")
	private String totalC007;
	@Column(name="TOTAL_C008")
	private String totalC008;
	@Column(name="TOTAL_C009")
	private String totalC009;
	@Column(name="ERROR_MESSAGE")
	private String errorMessage;
	@CreationTimestamp
	private LocalDate createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	private LocalDate modifiedDt;
	@Column(columnDefinition = "char")
	private String enabled;
	@OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name ="T_ES_MULTIADD_HEADER_ID")
	private List<EnergySaleMultiAddLine> energySaleMultiAddLines;
	
	public EnergySaleMultiAddHeader() {
		super();
	}

	public EnergySaleMultiAddHeader(String id, String energySaleId, String generationStatementId, String batchId,
			Integer totalCount, Integer successCount, Integer errorCount, String remarks, String sellerCompanyServiceId,
			LocalDate fromDt, LocalDate toDt, String totalC1, String totalC2, String totalC3, String totalC4,
			String totalC5, String totalC001, String totalC002, String totalC003, String totalC004, String totalC005,
			String totalC006, String totalC007, String totalC008, String totalC009, String errorMessage,
			LocalDate createdDt, String modifiedBy, LocalDate modifiedDt, String enabled,
			List<EnergySaleMultiAddLine> energySaleMultiAddLines) {
		super();
		this.id = id;
		this.energySaleId = energySaleId;
		this.generationStatementId = generationStatementId;
		this.batchId = batchId;
		this.totalCount = totalCount;
		this.successCount = successCount;
		this.errorCount = errorCount;
		this.remarks = remarks;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.totalC1 = totalC1;
		this.totalC2 = totalC2;
		this.totalC3 = totalC3;
		this.totalC4 = totalC4;
		this.totalC5 = totalC5;
		this.totalC001 = totalC001;
		this.totalC002 = totalC002;
		this.totalC003 = totalC003;
		this.totalC004 = totalC004;
		this.totalC005 = totalC005;
		this.totalC006 = totalC006;
		this.totalC007 = totalC007;
		this.totalC008 = totalC008;
		this.totalC009 = totalC009;
		this.errorMessage = errorMessage;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.enabled = enabled;
		this.energySaleMultiAddLines = energySaleMultiAddLines;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnergySaleId() {
		return energySaleId;
	}

	public void setEnergySaleId(String energySaleId) {
		this.energySaleId = energySaleId;
	}

	public String getGenerationStatementId() {
		return generationStatementId;
	}

	public void setGenerationStatementId(String generationStatementId) {
		this.generationStatementId = generationStatementId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}

	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}

	public LocalDate getFromDt() {
		return fromDt;
	}

	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}

	public LocalDate getToDt() {
		return toDt;
	}

	public void setToDt(LocalDate toDt) {
		this.toDt = toDt;
	}

	public String getTotalC1() {
		return totalC1;
	}

	public void setTotalC1(String totalC1) {
		this.totalC1 = totalC1;
	}

	public String getTotalC2() {
		return totalC2;
	}

	public void setTotalC2(String totalC2) {
		this.totalC2 = totalC2;
	}

	public String getTotalC3() {
		return totalC3;
	}

	public void setTotalC3(String totalC3) {
		this.totalC3 = totalC3;
	}

	public String getTotalC4() {
		return totalC4;
	}

	public void setTotalC4(String totalC4) {
		this.totalC4 = totalC4;
	}

	public String getTotalC5() {
		return totalC5;
	}

	public void setTotalC5(String totalC5) {
		this.totalC5 = totalC5;
	}

	public String getTotalC001() {
		return totalC001;
	}

	public void setTotalC001(String totalC001) {
		this.totalC001 = totalC001;
	}

	public String getTotalC002() {
		return totalC002;
	}

	public void setTotalC002(String totalC002) {
		this.totalC002 = totalC002;
	}

	public String getTotalC003() {
		return totalC003;
	}

	public void setTotalC003(String totalC003) {
		this.totalC003 = totalC003;
	}

	public String getTotalC004() {
		return totalC004;
	}

	public void setTotalC004(String totalC004) {
		this.totalC004 = totalC004;
	}

	public String getTotalC005() {
		return totalC005;
	}

	public void setTotalC005(String totalC005) {
		this.totalC005 = totalC005;
	}

	public String getTotalC006() {
		return totalC006;
	}

	public void setTotalC006(String totalC006) {
		this.totalC006 = totalC006;
	}

	public String getTotalC007() {
		return totalC007;
	}

	public void setTotalC007(String totalC007) {
		this.totalC007 = totalC007;
	}

	public String getTotalC008() {
		return totalC008;
	}

	public void setTotalC008(String totalC008) {
		this.totalC008 = totalC008;
	}

	public String getTotalC009() {
		return totalC009;
	}

	public void setTotalC009(String totalC009) {
		this.totalC009 = totalC009;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public List<EnergySaleMultiAddLine> getEnergySaleMultiAddLines() {
		return energySaleMultiAddLines;
	}

	public void setEnergySaleMultiAddLines(List<EnergySaleMultiAddLine> energySaleMultiAddLines) {
		this.energySaleMultiAddLines = energySaleMultiAddLines;
	}

	@Override
	public String toString() {
		return "EnergySaleMultiAddHeader [id=" + id + ", energySaleId=" + energySaleId + ", generationStatementId="
				+ generationStatementId + ", batchId=" + batchId + ", totalCount=" + totalCount + ", successCount="
				+ successCount + ", errorCount=" + errorCount + ", remarks=" + remarks + ", sellerCompanyServiceId="
				+ sellerCompanyServiceId + ", fromDt=" + fromDt + ", toDt=" + toDt + ", totalC1=" + totalC1
				+ ", totalC2=" + totalC2 + ", totalC3=" + totalC3 + ", totalC4=" + totalC4 + ", totalC5=" + totalC5
				+ ", totalC001=" + totalC001 + ", totalC002=" + totalC002 + ", totalC003=" + totalC003 + ", totalC004="
				+ totalC004 + ", totalC005=" + totalC005 + ", totalC006=" + totalC006 + ", totalC007=" + totalC007
				+ ", totalC008=" + totalC008 + ", totalC009=" + totalC009 + ", errorMessage=" + errorMessage
				+ ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", enabled=" + enabled + ", energySaleMultiAddLines=" + energySaleMultiAddLines + "]";
	}

	
		
	
	

}
