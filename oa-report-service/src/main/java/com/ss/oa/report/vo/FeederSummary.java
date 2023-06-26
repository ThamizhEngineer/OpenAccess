package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "M_FEEDER")
@Getter
public class FeederSummary {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String code;
	private String name;
	private String voltage;
	@Column(name="M_SUBSTATION_ID", columnDefinition="VARCHAR2")
	private String substationId;
	
	public FeederSummary() {
		super();
	}

	public FeederSummary(String id, String code, String name, String voltage, String substationId) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.voltage = voltage;
		this.substationId = substationId;
	}

	@Override
	public String toString() {
		return "FeederSummary [id=" + id + ", code=" + code + ", name=" + name + ", voltage=" + voltage
				+ ", substationId=" + substationId + "]";
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
	
}
