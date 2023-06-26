package com.ss.oa.transaction.MeterChange;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Table(name = "METER_CHANGE_LIST")
@Entity
@Transactional
public class MeterChangeList {
	
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "SERVICE_NO")
	private String Serviceno;
	@Column(name = "NEWMETER_NO")
	private String Newmwterno;
	@Column(name = "OLDMETER_NO")
	private String Oldmeterno;
	@Column(name = "FLOW_TYPE")
	private String Flowtype;
	@Column(name = "FUEL")
	private String Fuel;
	@Column(name = "REMARKS")
	private String Remarks;
	@Column(name = "TYPE_OF_SS")
	private String Typeofss;
	@Column(name = "CIRCLENAME")
	private String Circlename;
	@Column(name = "M_COMPANY_METER_ID")
	private String Meterid;
	@Column(name = "MONTH")
	private Integer Month;
	@Column(name = "YEAR")
	private Integer Year;
	@Column(name = "METERCHANGE_DATE")
	private Date MeterChangeDate;
	@Column(name = "IS_PROCESSED")
	private String Isprocessed;
	
	
	
	
	
	public MeterChangeList(String id, String serviceno, String newmwterno, String oldmeterno, String flowtype,
			String fuel, String remarks, String typeofss, String circlename, String meterid, Integer month,
			Integer year, Date meterChangeDate, String isprocessed) {
		super();
		this.id = id;
		Serviceno = serviceno;
		Newmwterno = newmwterno;
		Oldmeterno = oldmeterno;
		Flowtype = flowtype;
		Fuel = fuel;
		Remarks = remarks;
		Typeofss = typeofss;
		Circlename = circlename;
		Meterid = meterid;
		Month = month;
		Year = year;
		MeterChangeDate = meterChangeDate;
		Isprocessed = isprocessed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsprocessed() {
		return Isprocessed;
	}
	public void setIsprocessed(String isprocessed) {
		Isprocessed = isprocessed;
	}
	public String getServiceno() {
		return Serviceno;
	}
	public void setServiceno(String serviceno) {
		Serviceno = serviceno;
	}
	public String getNewmwterno() {
		return Newmwterno;
	}
	public void setNewmwterno(String newmwterno) {
		Newmwterno = newmwterno;
	}
	public String getOldmeterno() {
		return Oldmeterno;
	}
	public void setOldmeterno(String oldmeterno) {
		Oldmeterno = oldmeterno;
	}
	public String getFlowtype() {
		return Flowtype;
	}
	public void setFlowtype(String flowtype) {
		Flowtype = flowtype;
	}
	public String getFuel() {
		return Fuel;
	}
	public void setFuel(String fuel) {
		Fuel = fuel;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getTypeofss() {
		return Typeofss;
	}
	public void setTypeofss(String typeofss) {
		Typeofss = typeofss;
	}
	public String getCirclename() {
		return Circlename;
	}
	public void setCirclename(String circlename) {
		Circlename = circlename;
	}
	public String getMeterid() {
		return Meterid;
	}
	public void setMeterid(String meterid) {
		Meterid = meterid;
	}
	public Integer getMonth() {
		return Month;
	}
	public void setMonth(Integer month) {
		Month = month;
	}
	public Integer getYear() {
		return Year;
	}
	public void setYear(Integer year) {
		Year = year;
	}
	public Date getMeterChangeDate() {
		return MeterChangeDate;
	}
	public void setMeterChangeDate(Date meterChangeDate) {
		MeterChangeDate = meterChangeDate;
	}
	@Override
	public String toString() {
		return "MeterChangeList [id=" + id + ", Serviceno=" + Serviceno + ", Newmwterno=" + Newmwterno + ", Oldmeterno="
				+ Oldmeterno + ", Flowtype=" + Flowtype + ", Fuel=" + Fuel + ", Remarks=" + Remarks + ", Typeofss="
				+ Typeofss + ", Circlename=" + Circlename + ", Meterid=" + Meterid + ", Month=" + Month + ", Year="
				+ Year + ", MeterChangeDate=" + MeterChangeDate + ", Isprocessed=" + Isprocessed + ", getId()="
				+ getId() + ", getIsprocessed()=" + getIsprocessed() + ", getServiceno()=" + getServiceno()
				+ ", getNewmwterno()=" + getNewmwterno() + ", getOldmeterno()=" + getOldmeterno() + ", getFlowtype()="
				+ getFlowtype() + ", getFuel()=" + getFuel() + ", getRemarks()=" + getRemarks() + ", getTypeofss()="
				+ getTypeofss() + ", getCirclename()=" + getCirclename() + ", getMeterid()=" + getMeterid()
				+ ", getMonth()=" + getMonth() + ", getYear()=" + getYear() + ", getMeterChangeDate()="
				+ getMeterChangeDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
		public MeterChangeList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
	
