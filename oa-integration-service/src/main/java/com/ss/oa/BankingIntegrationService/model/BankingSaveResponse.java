package com.ss.oa.BankingIntegrationService.model;

import java.util.List;

public class BankingSaveResponse {
	
	private String status;
	
	private List<BankingSavrResponseImp> remarks;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<BankingSavrResponseImp> getRemarks() {
		return remarks;
	}
	public void setRemarks(List<BankingSavrResponseImp> remarks) {
		this.remarks = remarks;
	}

}
