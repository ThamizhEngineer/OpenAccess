package com.ss.oa.vo;

import java.util.ArrayList;
import java.util.List;

public class EnergyCharge {
	private String id,charge,code,description,int_adjusted_unit_id;
	private List<EnergySource> energySources;
	public EnergyCharge() {
		super();
		this.energySources = new ArrayList<EnergySource>();
	}
	public EnergyCharge(String id,String charge, String code, String description,String int_adjusted_unit_id, List<EnergySource> energySources) {
		super();
		this.id= id;
		this.charge = charge;
		this.code = code;
		this.description = description;
		this.int_adjusted_unit_id= int_adjusted_unit_id;
		this.energySources = energySources;
	
	}
	@Override
	public String toString() {
		return "EnergyCharge [id=" + id + ", charge=" + charge + ", code=" + code + ", description=" + description
				+ ", int_adjusted_unit_id=" + int_adjusted_unit_id + ", energySources=" + energySources + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInt_adjusted_unit_id() {
		return int_adjusted_unit_id;
	}
	public void setInt_adjusted_unit_id(String int_adjusted_unit_id) {
		this.int_adjusted_unit_id = int_adjusted_unit_id;
	}
	public List<EnergySource> getEnergySources() {
		return energySources;
	}
	public void setEnergySources(List<EnergySource> energySources) {
		this.energySources = energySources;
	}
	
}
