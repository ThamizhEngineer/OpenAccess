
package com.ss.oa.integration.updateDrawalVoltageService;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name="SERVICEMASVIEW")

public class UpdateDrawalVoltage {

    @Id
    @Column(name = "SERVICE_NO", unique = true, nullable = false)

	private String serviceNo;
	private String voltage;
	public UpdateDrawalVoltage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateDrawalVoltage(String serviceNo, String voltage) {
		super();
		this.serviceNo = serviceNo;
		this.voltage = voltage;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	@Override
	public String toString() {
		return "UpdateDrawalVoltage [serviceNo=" + serviceNo + ", voltage=" + voltage + "]";
	}
	
	
}
