//package com.ss.oa.report.vo;
//
//
//import javax.persistence.Column;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//
//
//@Table(name="V_ENERGY_ALLOC_AND_ADJ_REPORT")
//@Entity
//public class EnergyAllocatedReport{
//	
//	@Id
//	@Column(name="ID")
//	private Integer id;
//	
//	@Column(name="DISP_SERVICE_NUMBER")
//	private String dispServiceNumber;
//	
//	@Column(name="STMT_MONTH")
//	private String stmtMonth;
//	
//	@Column(name="NET_GENERATION")
//	private String netGeneration;
//	
//	@Column(name="ALLOTED_GROSS")
//	private String allotedGross;
//	
//	@Column(name="ALLOTED_NET")
//	private String allotedNet;
//	
//	@Column(name="ADJUSTED_NET")
//	private String adjustedNet;
//	
//	@Column(name="HT_BAKING")
//	private String htBanking;
//	
//	@Column(name="CLOSING_WITH_SURPLUS")
//	private String closingWithSurplus;
//	
//	@Column(name="OPENING_BALANCE")
//	private String openingBalance;
//	
//	
//	public EnergyAllocatedReport() {
//		super();
//	}
//
//
//	public EnergyAllocatedReport(Integer id, String dispServiceNumber, String stmtMonth, String netGeneration,
//			String allotedGross, String allotedNet, String adjustedNet, String htBanking, String closingWithSurplus,
//			String openingBalance) {
//		super();
//		this.id = id;
//		this.dispServiceNumber = dispServiceNumber;
//		this.stmtMonth = stmtMonth;
//		this.netGeneration = netGeneration;
//		this.allotedGross = allotedGross;
//		this.allotedNet = allotedNet;
//		this.adjustedNet = adjustedNet;
//		this.htBanking = htBanking;
//		this.closingWithSurplus = closingWithSurplus;
//		this.openingBalance = openingBalance;
//	}
//
//
//	public Integer getId() {
//		return id;
//	}
//
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//
//	public String getDispServiceNumber() {
//		return dispServiceNumber;
//	}
//
//
//	public void setDispServiceNumber(String dispServiceNumber) {
//		this.dispServiceNumber = dispServiceNumber;
//	}
//
//
//	public String getStmtMonth() {
//		return stmtMonth;
//	}
//
//
//	public void setStmtMonth(String stmtMonth) {
//		this.stmtMonth = stmtMonth;
//	}
//
//
//	public String getNetGeneration() {
//		return netGeneration;
//	}
//
//
//	public void setNetGeneration(String netGeneration) {
//		this.netGeneration = netGeneration;
//	}
//
//
//	public String getAllotedGross() {
//		return allotedGross;
//	}
//
//
//	public void setAllotedGross(String allotedGross) {
//		this.allotedGross = allotedGross;
//	}
//
//
//	public String getAllotedNet() {
//		return allotedNet;
//	}
//
//
//	public void setAllotedNet(String allotedNet) {
//		this.allotedNet = allotedNet;
//	}
//
//
//	public String getAdjustedNet() {
//		return adjustedNet;
//	}
//
//
//	public void setAdjustedNet(String adjustedNet) {
//		this.adjustedNet = adjustedNet;
//	}
//
//
//	public String getHtBanking() {
//		return htBanking;
//	}
//
//
//	public void setHtBanking(String htBanking) {
//		this.htBanking = htBanking;
//	}
//
//
//	public String getClosingWithSurplus() {
//		return closingWithSurplus;
//	}
//
//
//	public void setClosingWithSurplus(String closingWithSurplus) {
//		this.closingWithSurplus = closingWithSurplus;
//	}
//
//
//	public String getOpeningBalance() {
//		return openingBalance;
//	}
//
//
//	public void setOpeningBalance(String openingBalance) {
//		this.openingBalance = openingBalance;
//	}
//
//
//	@Override
//	public String toString() {
//		return "EnergyAllocatedReport [id=" + id + ", dispServiceNumber=" + dispServiceNumber + ", stmtMonth="
//				+ stmtMonth + ", netGeneration=" + netGeneration + ", allotedGross=" + allotedGross + ", allotedNet="
//				+ allotedNet + ", adjustedNet=" + adjustedNet + ", htBanking=" + htBanking + ", closingWithSurplus="
//				+ closingWithSurplus + ", openingBalance=" + openingBalance + "]";
//	}
//
//}