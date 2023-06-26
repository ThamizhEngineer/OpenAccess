package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Feeder {
	//substationName is not stored
	private String id, code, name, voltage, substationId, substationName, remarks, enabled;
	public Feeder() {
		super();
	}
	public Feeder(String id, String code, String name, String voltage, String substationId, String substationName,
			String remarks, String enabled) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.voltage = voltage;
		this.substationId = substationId;
		this.substationName = substationName;
		this.remarks = remarks;
		this.enabled = enabled;
	}
	public String getSubstationName() {
		return substationName;
	}
	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getSubstationId() {
		return substationId;
	}
	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "Feeder [id=" + id + ", code=" + code + ", name=" + name + ", voltage=" + voltage + ", substationId="
				+ substationId + ", substationName=" + substationName + ", remarks=" + remarks + ", enabled=" + enabled
				+ "]";
	}
	

}
