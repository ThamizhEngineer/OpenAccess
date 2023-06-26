package com.ss.oa.transaction.MeterChange;

public class MeterChangeResponse {
	
	private String ServiceNo;
	private String Result;
	
	
	@Override
	public String toString() {
		return "MeterChangeResponse [ServiceNo=" + ServiceNo + ", Result=" + Result + ", getServiceNo()="
				+ getServiceNo() + ", getResult()=" + getResult() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getServiceNo() {
		return ServiceNo;
	}
	public void setServiceNo(String serviceNo) {
		ServiceNo = serviceNo;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	

}
