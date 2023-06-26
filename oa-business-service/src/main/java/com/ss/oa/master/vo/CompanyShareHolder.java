package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class CompanyShareHolder {

	
	private String id, share,companyId,companyCode,companyName, 
					shareHolderCompanyId, shareHolderCompanyCode,shareHolderCompanyName,measure,enabled,shareHolderCompanyServiceId,quantum;


	public CompanyShareHolder() {
	}


	public CompanyShareHolder(String id, String share, String companyId, String companyCode, String companyName,
			String shareHolderCompanyId, String shareHolderCompanyCode, String shareHolderCompanyName, String measure,
			String enabled) {
		super();
		
	}


	@Override
	public String toString() {
		return "CompanyShareHolder [id=" + id + ", share=" + share + ", companyId=" + companyId + ", companyCode="
				+ companyCode + ", companyName=" + companyName + ", shareHolderCompanyId=" + shareHolderCompanyId
				+ ", shareHolderCompanyCode=" + shareHolderCompanyCode + ", shareHolderCompanyName="
				+ shareHolderCompanyName + ", measure=" + measure + ", enabled=" + enabled
				+ ", shareHolderCompanyServiceId=" + shareHolderCompanyServiceId + ", quantum=" + quantum + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getShare() {
		return share;
	}


	public void setShare(String share) {
		this.share = share;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getCompanyCode() {
		return companyCode;
	}


	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getShareHolderCompanyId() {
		return shareHolderCompanyId;
	}


	public void setShareHolderCompanyId(String shareHolderCompanyId) {
		this.shareHolderCompanyId = shareHolderCompanyId;
	}


	public String getShareHolderCompanyCode() {
		return shareHolderCompanyCode;
	}


	public void setShareHolderCompanyCode(String shareHolderCompanyCode) {
		this.shareHolderCompanyCode = shareHolderCompanyCode;
	}


	public String getShareHolderCompanyName() {
		return shareHolderCompanyName;
	}


	public void setShareHolderCompanyName(String shareHolderCompanyName) {
		this.shareHolderCompanyName = shareHolderCompanyName;
	}


	public String getMeasure() {
		return measure;
	}


	public void setMeasure(String measure) {
		this.measure = measure;
	}


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public String getShareHolderCompanyServiceId() {
		return shareHolderCompanyServiceId;
	}


	public void setShareHolderCompanyServiceId(String shareHolderCompanyServiceId) {
		this.shareHolderCompanyServiceId = shareHolderCompanyServiceId;
	}


	public String getQuantum() {
		return quantum;
	}


	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}


	public CompanyShareHolder(String id, String share, String companyId, String companyCode, String companyName,
			String shareHolderCompanyId, String shareHolderCompanyCode, String shareHolderCompanyName, String measure,
			String enabled, String shareHolderCompanyServiceId, String quantum) {
		super();
		this.id = id;
		this.share = share;
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.shareHolderCompanyId = shareHolderCompanyId;
		this.shareHolderCompanyCode = shareHolderCompanyCode;
		this.shareHolderCompanyName = shareHolderCompanyName;
		this.measure = measure;
		this.enabled = enabled;
		this.shareHolderCompanyServiceId = shareHolderCompanyServiceId;
		this.quantum = quantum;
	}

}
