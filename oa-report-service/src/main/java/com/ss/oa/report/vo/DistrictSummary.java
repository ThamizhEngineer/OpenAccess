package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "M_DISTRICT")
@Getter
public class DistrictSummary {
	@Id
	@Column(name="DISTRICT_CODE", columnDefinition="VARCHAR2")
	private String districtCode;
	@Column(name="DISTRICT_NAME", columnDefinition="VARCHAR2")
	private String districtName;
	private String status;
	@Column(name="CIRCLE_CODE", columnDefinition="VARCHAR2")
	private String circleCode;
	public DistrictSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DistrictSummary(String districtCode, String districtName, String status, String circleCode) {
		super();
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.status = status;
		this.circleCode = circleCode;
	}
	@Override
	public String toString() {
		return "DistrictSummary [districtCode=" + districtCode + ", districtName=" + districtName + ", status=" + status
				+ ", circleCode=" + circleCode + "]";
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCircleCode() {
		return circleCode;
	}
	public void setCircleCode(String circleCode) {
		this.circleCode = circleCode;
	}
	
}
