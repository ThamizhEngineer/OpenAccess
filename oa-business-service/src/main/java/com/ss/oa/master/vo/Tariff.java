package com.ss.oa.master.vo;

import java.util.Date;

import org.springframework.context.annotation.Scope;
@Scope("prototype")
public class Tariff {
	String id,type,weg_group_code,rate,reference;
	Date from_date,to_date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWeg_group_code() {
		return weg_group_code;
	}
	public void setWeg_group_code(String weg_group_code) {
		this.weg_group_code = weg_group_code;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

}
